# demo

This is an empty project of Spring boot.

## cmd
1. mvn clean package spring-boot:repackage
2. sh cmd.sh [start|stop|restart|status]

## 配置中心
1. 增加了apollo 支持,使用了mgzf-sdk-cfgcenter包
2. 增加了swagger 支持,使用springfox-swagger2 的 2.7.0 UI版本
3. 去掉mogo配置中心
4. 使用统一异常:

~~~
#出现错误时,直接抛出异常,让RobbieExceptionHandler接管NoHandlerFoundException
spring.mvc.throw-exception-if-no-handler-found=true
#不要为我们工程中的资源文件建立映射
spring.resources.add-mappings=false

/*
这样操作后，发现确实可以抛出NoHandlerFoundException异常了，
但是由于我们上面配置关闭了静态资源映射，所以这时候我们项目的静态资源没法访问了，我们需要手动定义静态资源的映射，
比如我们集成了swagger，会发现/swagger-ui.html页面没法访问了，我们需要手动定义静态资源映射
https://blog.csdn.net/qq_36666651/article/details/81135139
*/
    WebMvcConfigurerAdapter

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry.addResourceHandler("/**")
                .addResourceLocations("classpath*:/static/");*/

        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
~~~