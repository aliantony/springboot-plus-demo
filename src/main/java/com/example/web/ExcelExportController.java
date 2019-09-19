package com.example.web;

import com.example.entity.SysMenu;
import com.example.service.MenuService;
import com.example.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
 
/**
 * <pre>
 * &#64;author aliantony
 * &#64;date 2018年12月13日 下午6:16:59
 * </pre>
 */
@RestController
@RequestMapping("/excel/export")
public class ExcelExportController {

	@Autowired
	private MenuService menuService;
 
	@GetMapping("/exportExcel")
	public void export(HttpServletResponse response) {
		System.out.println(1);
		// 模拟从数据库获取需要导出的数据
		List<SysMenu> personList = menuService.findAll();
		// 导出操作
		ExcelUtils.exportExcel(personList, "easypoi导出功能", "导出sheet1", SysMenu.class, "测试菜单menu.xls", response);
 
	}
}
