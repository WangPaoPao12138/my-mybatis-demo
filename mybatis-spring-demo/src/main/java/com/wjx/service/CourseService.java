package com.wjx.service;

import com.wjx.entity.Course;

import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/8/31 10:19
 */
public interface CourseService {
    List<Course> queryCourse();

    int addCourse(Course course);
}
