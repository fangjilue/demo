package me.robbie.spring.demo.test;

import me.robbie.spring.demo.dao.StudentDao;
import me.robbie.spring.demo.model.Student;
import me.robbie.spring.demo.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * mock dao
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/30 下午3:33
 * @since [产品/模块版本]
 */
public class StudentServiceTest {

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

        when(studentDao.save(anyObject())).thenReturn(200);
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
        Student student2 = new Student();
        student2.setSex(0);
        student2.setNo("NO.2");
        student2.setName("mock2");
        student2.setId(2);

        Integer id  = studentService.save(student2);

        System.out.println("result ="+id);
    }

}
