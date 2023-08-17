package com.example.controller;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;


public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("自定义对应列的标题")
    //@ExcelProperty这个注解可以省略，可以通过head来添加列标题，但是要注意，属性的值要和列名相对应
    //如果需要值类型枚举转换，则需要写一个枚举转换类，注解中convertor指定，这里不过多赘述
    //@ExcelProperty(value = "性别（0：女，1：男）"，convertor = 枚举转换.class)
    //private GenderType gender;或者是private Integer gender;
    private Integer id;

    @ExcelProperty("自定义对应列的标题")
    private String name;

    @ExcelProperty("自定义对应列的标题")
    private Integer age;

    @ExcelProperty("自定义对应列的标题")
    private String sex;


    public Student(Integer id, String name, Integer age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
