package me.robbie.spring.demo.test;

import me.robbie.spring.demo.dao.StudentDao;
import me.robbie.spring.demo.model.Student;
import me.robbie.spring.demo.service.StudentService;
import me.robbie.spring.demo.util.ValidatorUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * mock dao and mock private
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/30 下午3:47
 * @since [产品/模块版本]
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({StudentService.class, ValidatorUtil.class})//私有方法，静态方法对应的类，多个之间逗号隔开。
public class StudentServicePrivateTest {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentService studentService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Student student = new Student();
        student.setSex(0);
        student.setNo("NO.x");
        student.setName("mock");
        student.setId(3);

        Student student1 = new Student();
        student1.setSex(0);
        student1.setNo("NO.1");
        student1.setName("mock1");
        student1.setId(1);

        Student student2 = new Student();
        student2.setSex(0);
        student2.setNo("NO.2");
        student2.setName("mock2");
        student2.setId(2);

        when(studentDao.get(anyInt())).thenReturn(student);
        when(studentDao.get(1)).thenReturn(student1);
        when(studentDao.get(2)).thenReturn(student2);

        doReturn(student2).when(studentDao).get(3);

        when(studentDao.save(anyObject())).thenReturn(100);
    }

    @Test
    public void testGet() {
        Student result1 = studentService.get(1);

        verify(studentDao, times(1)).get(1);
        assertThat(result1.getName(), is("mock1"));


        Student result3 = studentService.get(3);

        verify(studentDao, times(1)).get(3);
        assertThat(result3.getName(), is("mock"));
    }


    @Test
    public void testSave() throws Exception{
        Student student3 = new Student();
        student3.setSex(0);
        student3.setNo("NO.3");
        student3.setName("mock3");
        student3.setId(3);

        Integer id  = studentService.save(student3);

        System.out.println("result ="+id);
    }


    @Test
    public void testMockPrivate() throws Exception{
        StudentService powerMock = PowerMockito.spy(studentService);

        Student student3 = new Student();
        student3.setSex(0);
        student3.setNo("NO.3");
        student3.setName("mock3");
        student3.setId(3);

        PowerMockito.when(powerMock, "getId", anyInt()).thenReturn(1);

        //必须使用 powerMock 对象操作
        Integer result = powerMock.save(student3);

        System.out.println("result ="+result);
    }



    @Test
    public void testModify(){
        Student student3 = new Student();
        student3.setSex(0);
        student3.setNo("NO.3");
        student3.setName("mock3");
        student3.setId(3);

        Boolean result = studentService.modify(student3);

        System.out.println("modify=" + result);
    }

    @Test
    public void testModifyMockDao(){
        Student student3 = new Student();
        student3.setSex(0);
        student3.setNo("NO.3");
        student3.setName("mock3");
        student3.setId(3);

        when(studentDao.modify(anyObject())).thenReturn(true);

        Boolean result = studentService.modify(student3);

        System.out.println("modify=" + result);
    }

    @Test
    public void testModifyMockDaoAndStatic() throws Exception {
        Student student3 = new Student();
        student3.setSex(0);
        student3.setNo("NO.3");
        student3.setName("mock3");
        student3.setId(3);

        when(studentDao.modify(anyObject())).thenReturn(true);

        PowerMockito.mockStatic(ValidatorUtil.class);

        Boolean result = studentService.modify(student3);

        System.out.println("modify=" + result);
    }


}
