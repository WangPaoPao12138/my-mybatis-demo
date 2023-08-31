package com.wjx.service.impl;

import com.wjx.dao.CourseDao;
import com.wjx.entity.Course;
import com.wjx.service.CourseService;

import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/8/31 10:19
 */
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> queryCourse() {
        return courseDao.selectCourse();
    }
}
