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

    //单insert循环插入
    int addCourseByForeach(List<Course> courseList);

    //批量插入 batchInsert
    int addCourseByBatch(List<Course> courseList);

    //批处理sql
    int insertBatchBySessionCommit(List<Course> courseList);
}
