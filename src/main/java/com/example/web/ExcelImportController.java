package com.example.web;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.SysMenu;
import com.example.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
 
/**
 * <pre>
 * &#64;author aliantony
 * &#64;date 2018年12月13日 下午6:17:10
 * </pre>
 */
@RestController
@RequestMapping("/excel/import")
@Slf4j
public class ExcelImportController {

	@Autowired
	private MenuService menuService;
 
	@PostMapping("/importExcel")
	public String importExcel2(@RequestParam("file") MultipartFile file) {
		ImportParams importParams = new ImportParams();
		// 数据处理
		//importParams.setHeadRows(0);
		importParams.setTitleRows(0);
		// 需要验证
		importParams.setNeedVerfiy(false);       
        
		try {
			ExcelImportResult<SysMenu> result = ExcelImportUtil.importExcelMore(file.getInputStream(), SysMenu.class,
					importParams);
			List<SysMenu> menuList = result.getList();
			for (SysMenu menu : menuList) {
				// System.out.println(User);
				log.info("从Excel导入数据到数据库的详细为 ：{}", JSONObject.toJSONString(menu));
				//TODO 将导入的数据做保存数据库操作
			}
			menuService.generateSql(menuList);
			log.info("从Excel导入数据一共 {} 行 ", menuList.size());
		} catch (IOException e) {
			log.error("导入失败：{}", e.getMessage());
		} catch (Exception e1) {
			log.error("导入失败：{}", e1.getMessage());
		}
		return "导入成功";
	}
}
