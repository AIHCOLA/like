package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    //主键
    private Long id;

    //类型 1 菜品分类 2 套餐分类          check约束，在数据库category表中设置了
    private Integer type;

    //分类名称
    private String name;

    //排序
    private Integer sort;

}
