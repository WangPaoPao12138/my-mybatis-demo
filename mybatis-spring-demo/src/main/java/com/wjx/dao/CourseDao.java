package com.wjx.dao;

import com.wjx.entity.Course;

import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/8/30 23:49
 */
public interface CourseDao {
    List<Course> selectCourse();
}
