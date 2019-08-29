package com.example.entity;

import com.example.enums.AgeEnum;
import com.example.enums.GenderEnum;
import com.example.enums.GradeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户实体对应表 user
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("用户实体")
public class User extends BaseEntity {

    private String name;

    @ApiModelProperty("用户邮箱")
    private String email;

    /**
     * IEnum接口的枚举处理
     */
    private AgeEnum age;

    /**
     * 原生枚举： 默认使用枚举值顺序： 0：MALE， 1：FEMALE
     */
    private GenderEnum gender;

    /**
     * 原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库的值对应该注解对应的属性
     */
    private GradeEnum grade;

}