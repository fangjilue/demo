package me.robbie.spring.demo.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-03-15 17:38
 * @since [产品/模块版本]
 */
public class NumberTest {

    @Test
    public void test(){
        BigDecimal num1 = new BigDecimal("3000.00");
        BigDecimal num2 = new BigDecimal("3000.10");
        BigDecimal num3 = new BigDecimal("3000.01");

        DecimalFormat df1 = new DecimalFormat("#.##");

        System.out.println(df1.format(num1));
        System.out.println(df1.format(num2));
        System.out.println(df1.format(num3));

        Integer n1= 1;
        Integer n2= 10;
        Integer n3= 100;
        Integer n4= 1000;
        Integer n5= 10000;

        System.out.println(String.format("%04d",n1));
        System.out.println(String.format("%04d",n2));
        System.out.println(String.format("%04d",n3));
        System.out.println(String.format("%04d",n4));
        System.out.println(String.format("%04d",n5));

    }
}
