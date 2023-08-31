import com.wjx.entity.Course;
import com.wjx.entity.Student;
import com.wjx.service.CourseService;
import com.wjx.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    public void test04(){
        //读取spring主配置文件
        String in = "ApplicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(in);
        StudentService studentService = (StudentService) ac.getBean("studentService");
        List<Student> studentList = studentService.queryStudents();
        for (Student stu:studentList) {
            System.out.println(">>>>"+stu);
        }
        CourseService courseService = (CourseService) ac.getBean("courseService");
        List<Course> courseList = courseService.queryCourse();
        for (Course course:courseList) {
            System.out.println("<<<<"+course);
        }
    }
}
