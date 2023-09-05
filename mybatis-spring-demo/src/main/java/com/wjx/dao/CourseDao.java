package com.wjx.dao;

import com.wjx.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/8/30 23:49
 */
public interface CourseDao {
    List<Course> selectCourse();
   int insertCourse(Course course);
   int insertCourseByBatch(@Param("courseList") List<Course> courseList);
   int updateCourse(Course course);
}
