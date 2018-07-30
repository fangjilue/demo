package me.robbie.spring.demo.dao;

import me.robbie.spring.demo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Random;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/30 下午2:33
 * @since [产品/模块版本]
 */
@Repository
public class StudentDao {

    public static final Logger logger = LoggerFactory.getLogger(StudentDao.class);

    public Integer save(Student student) {
        logger.debug("save student ={}",student);

        return 1;
    }


    public boolean modify(Student student) {
        logger.debug("dao modify....");
        return true;

    }

    public Student get(Integer id) {
        logger.debug("get student id={}", id);

        Student student = new Student();
        student.setClassId(1);
        student.setName("张三");
        if (id == 1) {
            student.setSex(1);
            student.setNo("NO.1");
        } else {
            student.setSex(0);
            student.setNo("NO."+id);
        }
        student.setId(id);
        return student;
    }
}
