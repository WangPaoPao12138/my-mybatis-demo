package com.wjx.service.impl;

import com.wjx.dao.CourseDao;
import com.wjx.entity.Course;
import com.wjx.service.CourseService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/8/31 10:19
 */
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;
    private SqlSessionFactory sqlSessionFactory;

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Course> queryCourse() {
        return courseDao.selectCourse();
    }

    @Override
    public int addCourse(Course course) {
        return courseDao.insertCourse(course);
    }


    //单insert循环插入
    @Override
    public int addCourseByForeach(List<Course> courseList) {
        long start = System.currentTimeMillis();

        int result = 0;
        for (Course c : courseList) {
            int i = courseDao.insertCourse(c);
            result += i;
        }

        //count: 100  time: 289ms 6015ms
        //count: 1000  time: 2028ms 9487ms
        //count: 10000  time: 25951ms 25926ms
        System.out.println("addCourseByForeach " + (System.currentTimeMillis() - start));
        return result;
    }

    //批量插入 batchInsert
    @Override
    public int addCourseByBatch(List<Course> courseList) {
        long start = System.currentTimeMillis();

        int result = 0;
        int eachSize = 1000;
        int length = courseList.size() % eachSize == 0 ? courseList.size() / eachSize : courseList.size() / eachSize + 1;
        for (int i = 0; i < length; i++) {
            int temp = (i + 1) * eachSize;
            if (temp >= courseList.size()) {
                temp = courseList.size();
            }
            List<Course> rowsList = courseList.subList(i * eachSize, temp);
            //提交的方法
            int count = courseDao.insertCourseByBatch(rowsList);
            result += count;
        }
//        result = courseDao.insertCourseByBatch(courseList);
        //count: 100  time: 6080ms 46ms
        //count: 1000  time: 9220ms 5800ms 132 ms
        //count: 10000  time: 429ms 430ms
        System.out.println("addCourseByBatch " + (System.currentTimeMillis() - start));
        return result;
    }

    //批处理sql
    @Override
    public int insertBatchBySessionCommit(List<Course> courseList) {
        long start = System.currentTimeMillis();

        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        CourseDao mapper = session.getMapper(CourseDao.class);
        for (int i = 0; i < courseList.size(); i++) {
            mapper.insertCourse(courseList.get(i));
            if (i % 1000 == 999) {//每1000条提交一次防止内存溢出
                session.commit();
                session.clearCache();
            }
        }
        session.commit();
        session.close();
        //count: 100  time: 188ms 622ms
        //count: 1000  time: 1422ms 1589ms
        //count: 10000  time: 15716ms 19378ms
        System.out.println("insertBatchBySessionCommit " + (System.currentTimeMillis() - start));
        return courseList.size();
    }
}
