package com.wjx.entity;

import com.wjx.annotation.TuoFeng;
import lombok.Data;

/**
 * @author Gin
 * @description
 * @date 2023/8/30 16:17
 */
@Data
//开启驼峰
@TuoFeng(value = true)
public class Course {
    private Integer id;
    private String courseName;
    //测试驼峰插件 开启驼峰
    private String testTuoFeng;
}
