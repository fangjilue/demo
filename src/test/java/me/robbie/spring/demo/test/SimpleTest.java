package me.robbie.spring.demo.test;

import org.junit.Test;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2018/8/27 下午3:31
 * @since [产品/模块版本]
 */
public class SimpleTest {

    @Test
    public void test(){

        Double ceiling = Math.ceil(4.2);
        int b = (int)Math.ceil(4.2);

        System.out.println(ceiling.intValue());
        System.out.println(b);
    }
}
