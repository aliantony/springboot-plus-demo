package com.example.service;

import com.example.entity.SysMenu;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
 
/**
 * <pre>
 * &#64;author cao_wencao
 * &#64;date 2018年12月13日 下午5:37:17
 * </pre>
 */
@Service
public class MenuService {
	
	public List<SysMenu> findAll() {
		List<SysMenu> listAll = Lists.newArrayList();
		List<SysMenu> list = Lists.newArrayList();
		SysMenu menu = new SysMenu();
		menu.setId(10);
		menu.setName("首页");
		menu.setParentId(1);
		menu.setMenuType(1);
		menu.setTag("shouye");
		SysMenu menu2 = new SysMenu();
		menu2.setId(11);
		menu2.setName("资产管理");
		menu2.setParentId(1);
		menu2.setMenuType(1);
		menu2.setTag("asset");
		SysMenu menu3 = new SysMenu();
		menu3.setId(12);
		menu3.setName("配置管理");
		menu3.setParentId(1);
		menu3.setMenuType(1);
		menu3.setTag("config");
		list.add(menu2);
		list.add(menu3);
		list.add(menu);
		listAll.addAll(list);
		return listAll;
	}

	public void generateSql(List<SysMenu> menus) throws Exception {
		List<String> sqlList = new ArrayList<>();
		StringBuilder sb = null;
		for (SysMenu menu : menus) {
			sb = new StringBuilder();
			sb.append("insert into sys_menu(id, parent_id, tag, name, menu_type) values (")
					.append(menu.getId() + ",")
					.append(menu.getParentId() + ",")
					.append("'" + menu.getTag() + "',")
					.append("'" + menu.getName() + "',")
					.append(menu.getMenuType() + ")");
			sqlList.add(sb.toString() + ";");
		}
		//获取classes目录绝对路径
		//String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		//String path = ResourceUtils.getURL("classpath:").getPath();
		//String path1 = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		//System.out.println(path1);
		File path = new File(ResourceUtils.getURL("classpath:").getPath());
		if (!path.exists()) {
			path = new File("");
		}
		System.out.println("path:" + path.getAbsolutePath());
		File sqlFile = new File("D:/mensql.sql");
		BufferedWriter writer = new BufferedWriter(new FileWriter(sqlFile));
		for (String sql : sqlList){
			writer.write(sql + "\r\n");
		}
		writer.close();
	}
}
