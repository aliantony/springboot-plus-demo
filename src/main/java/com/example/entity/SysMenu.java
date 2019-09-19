package com.example.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 菜单表
 *
 * @author wangqian
 * @since 2019-01-15
 * http://easypoi.mydoc.io/#text_197835
 */
@Setter
@Getter
@ToString
public class SysMenu implements Serializable {

  private static final long serialVersionUID = 1L;

  @Excel(name = "主键", width=15)
  @NotBlank(message = "该字段不能为空")
  private Integer id;

  @Excel(name = "父主键", width=30)
  private Integer parentId;

  @Excel(name = "菜单标识", width=30)
  private String tag;

  @Excel(name = "菜单名称", width=30)
  private String name;

  @Excel(name = "菜单类型", width=30)
  private Integer menuType;


}
