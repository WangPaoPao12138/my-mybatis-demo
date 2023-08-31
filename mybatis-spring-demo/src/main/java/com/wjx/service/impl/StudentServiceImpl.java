package com.wjx.service.impl;

import com.wjx.dao.StudentDao;
import com.wjx.entity.Student;
import com.wjx.service.StudentService;

import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/8/30 17:22
 */

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    @Override
    public int addStudent(Student student) {
        return 0;
    }

    @Override
    public List<Student> queryStudents() {
        return studentDao.selectStudent();
    }

    @Override
    public List<Student> selectStudentById(int id) {
        return null;
    }
}
