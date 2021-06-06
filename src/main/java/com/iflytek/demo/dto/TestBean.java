package com.iflytek.demo.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 18:46 2019/5/8
 */
public class TestBean {
    // 通过属性名绑定csv列名, 属性名必须与csv列名相同
    @CsvBindByName
    private Integer id;

    // 解析Date格式
    @CsvBindByName
    @CsvDate("yyyy-MM-dd")
    private Date date;

    // csv列名与字段名不同时
    @CsvBindByName(column = "name")
    private String name;

    // csv不存在列名时也可通过位置绑定, 如 `@CsvBindByPosition(position = 0)   `

    // getter, setter and toString() ...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}