import com.wjx.entity.Course;
import com.wjx.entity.Student;
import com.wjx.service.CourseService;
import com.wjx.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/8/30 17:29
 */
public class TestMybatisSpringDemo {
    /*使用Service*/
//    @Test
//    public void test03(){
//        //读取spring主配置文件
//        String in = "ApplicationContext.xml";
//        ApplicationContext ac = new ClassPathXmlApplicationContext(in);
//        StudentService service = (StudentService) ac.getBean("ServiceStudent");
////        Student student = new Student(4002,"裴元虎","peiyuanhu@163.com",26);
////        int i = service.addStudent(student);
//        System.out.println("添加"+"行数据");
//    }

    @Test
    public void testForSelect() {
        //读取spring主配置文件
        String in = "ApplicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(in);
        StudentService studentService = (StudentService) ac.getBean("studentService");
        List<Student> studentList = studentService.queryStudents();
        for (Student stu : studentList) {
            System.out.println(">>>>" + stu);
        }
//        CourseService courseService = (CourseService) ac.getBean("courseService");
//        List<Course> courseList = courseService.queryCourse();
//        for (Course course : courseList) {
//            System.out.println("<<<<" + course);
//        }
    }

    @Test
    public void testForInsert() {
        //读取spring主配置文件
        String in = "ApplicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(in);
        StudentService studentService = (StudentService) ac.getBean("studentService");
        Student student = new Student();
        student.setName("王图图");
        student.setAge(3);
        student.setAddress("翻斗大街");
        student.setTestTuoFeng("不开启");
        studentService.addStudent(student);

        CourseService courseService = (CourseService) ac.getBean("courseService");
        Course course = new Course();
        course.setCourseName("MybatisPlus");
        course.setTestTuoFeng("开启");
        courseService.addCourse(course);
    }

    @Test
    public void testForBatchInsert() {
        //读取spring主配置文件
        String in = "ApplicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(in);
        CourseService courseService = (CourseService) ac.getBean("courseService");
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Course course = new Course();
            course.setCourseName("test" + i);
            course.setTestTuoFeng("开启" + i);
            courseList.add(course);
        }
        //TODO 后续得找一个云服务器测试 本地电脑可能有问题
        //count : 100  time : 4633
        //count : 1000  time : 6391 6947
        //count : 10000  time : 18239 19385 18465
        courseService.addCourseByForeach(courseList);
        //count : 100  time :  137
        //count : 1000  time :  1578 1250
        //count : 10000  time :  16698 11218 11084
        courseService.insertBatchBySessionCommit(courseList);
        //count : 100  time :  41
        //count : 1000  time :  106 85
        //count : 10000  time :  377 383 355
        courseService.addCourseByBatch(courseList);
    }

}
