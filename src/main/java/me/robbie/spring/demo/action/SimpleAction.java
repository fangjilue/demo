package me.robbie.spring.demo.action;

import me.robbie.spring.demo.config.ApolloAnnotationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/30 下午2:03
 * @since [产品/模块版本]
 */
@RestController
public class SimpleAction {

    @Autowired
    private ApolloAnnotationBean testApolloAnnotationBean;


    @RequestMapping("/")
    String index() {

        return "Hello World!" + testApolloAnnotationBean.getName();
    }
}
