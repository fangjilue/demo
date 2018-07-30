package me.robbie.spring.demo.service;

import me.robbie.spring.demo.dao.StudentDao;
import me.robbie.spring.demo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/30 下午2:32
 * @since [产品/模块版本]
 */
@Service
public class StudentService {
    public static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentDao studentDao;

    public Integer save(Student student){
        logger.debug("save...");
        Integer id = studentDao.save(student);
        return getId(id);
    }


    public boolean modify(Student student){
        logger.debug("modify...");

        return studentDao.modify(student);
    }

    public Student get(Integer id){
        logger.debug("get...");

        if(id == null){
            return null;
        }
        return studentDao.get(id);
    }


    private Integer getId(Integer id){
        logger.debug("getId id ={}",id);
        return id +1;
    }

}
