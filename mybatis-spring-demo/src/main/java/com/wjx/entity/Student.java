package com.wjx.entity;

import com.wjx.annotation.TuoFeng;
import lombok.Data;

/**
 * @author Gin
 * @description
 * @date 2023/8/30 16:10
 */
@Data
//不开启驼峰
//@TuoFeng(value = false)
public class Student {
    //属性名和列名一样
    private Integer id;
    private String name;
    private String address;
    private Integer age;
    //测试驼峰插件 不开启驼峰
    private String testTuoFeng;
}