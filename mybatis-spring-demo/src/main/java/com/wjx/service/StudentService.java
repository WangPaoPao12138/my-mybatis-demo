package com.wjx.service;

import com.wjx.entity.Student;

import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/8/30 17:21
 */
public interface StudentService {

    int addStudent(Student student);

    List<Student> queryStudents();

    List<Student> selectStudentById(int id);

}