package me.robbie.spring.demo.config;

//import com.ctrip.framework.apollo.Config;
//import com.ctrip.framework.apollo.model.ConfigChangeEvent;
//import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
//import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/31 下午6:22
 * @since [产品/模块版本]
 */
@Component
public class ApolloAnnotationBean {


    //@ApolloConfig
    //private Config config; //inject config for namespace application

    @Value("${tp.demo.name:100}")
    private String name;

    //config change listener for namespace application
    /*@ApolloConfigChangeListener
    private void someOnChange(ConfigChangeEvent changeEvent) {
        //update injected value of batch if it is changed in Apollo
        if (changeEvent.isChanged("tp.demo.name")) {
            name = config.getProperty("tp.demo.name", "100");
            System.out.println("name====" + name);
        }
    }*/


    public String getName() {
        return name;
    }
}
