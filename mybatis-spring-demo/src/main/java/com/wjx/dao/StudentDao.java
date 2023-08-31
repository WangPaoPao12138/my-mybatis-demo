package com.wjx.dao;

import com.wjx.entity.Student;

import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/8/30 16:19
 */
public interface StudentDao {
    List<Student> selectStudent();
}
