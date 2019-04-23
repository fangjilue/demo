package me.robbie.spring.demo;

//import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

import me.robbie.spring.demo.freemarker.FreeMarkerLayoutView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Arrays;

/**
 * @SpringBootApplication 集成了
 * @SpringBootConfiguration 等价于Configuration
 * @EnableAutoConfiguration
 * @ComponentScan
 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
@ImportResource("classpath:META-INF/spring/context.xml")
//@EnableApolloConfig//apollo 配置注解
public class Run {

    public static final Logger logger = LoggerFactory.getLogger(Run.class);


    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Run.class, args);

        ApplicationArguments applicationArguments = context.getBean(ApplicationArguments.class);
        System.out.println("============Run main===========");
        for (String name : applicationArguments.getOptionNames()) {
            System.out.println("name=" + name + ",value=" + applicationArguments.getOptionValues(name));
        }

        /*
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println("beanName=" + beanName);
        }*/
    }


    @Bean
    public CommandLineRunner customFreemarker(FreeMarkerViewResolver resolver){
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                //增加视图
                resolver.setViewClass(FreeMarkerLayoutView.class);
            }
        };
    }

}