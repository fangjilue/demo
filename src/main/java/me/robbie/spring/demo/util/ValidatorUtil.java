package me.robbie.spring.demo.util;

import me.robbie.spring.demo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2018/9/13 下午5:31
 * @since [产品/模块版本]
 */
public class ValidatorUtil {
    public static final Logger logger = LoggerFactory.getLogger(ValidatorUtil.class);

    public static void validate(Student student){
        logger.debug("ValidatorUtil#validate...");
        Assert.notNull(student,"student is not null");
        Assert.notNull(student.getName(),"student name is not null");
        Assert.notNull(student.getNo(),"student no is not null");
    }
}
