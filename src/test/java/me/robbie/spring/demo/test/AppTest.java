package me.robbie.spring.demo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import me.robbie.spring.demo.Run;
import me.robbie.spring.demo.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 不用单独启动应用直接测试 action
 *
 * @RunWith(SpringRunner.class) 等价于
 * @RunWith(SpringJUnit4ClassRunner.class)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Run.class)
@WebAppConfiguration
public class AppTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testIndex() throws Exception{
        ResultActions actions =
                mvc.perform(MockMvcRequestBuilders.get("/")
                        .accept(MediaType.TEXT_PLAIN))
                        .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.content().string("Hello World!"));

        System.out.println(actions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testSave()  throws Exception {
        Student student = new Student();
        student.setName("李四");
        student.setNo("no2");
        student.setSex(0);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(student);

        ResultActions actions =
                mvc.perform(MockMvcRequestBuilders.post("/student/save")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(requestJson))
                        .andDo(new ResultHandler() {
                    @Override
                    public void handle(MvcResult result) throws Exception {
                        System.out.println(result.getResponse().getContentAsString());
                    }
                }).andExpect(MockMvcResultMatchers.status().isOk());
        //.andExpect(MockMvcResultMatchers.content().string("Hello World!"));
    }


    @Test
    public void testModify()  throws Exception {
        Student student = new Student();
        student.setName("李四");
        student.setNo("no2");
        student.setSex(0);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(student);

        ResultActions actions =
                mvc.perform(MockMvcRequestBuilders.put("/student/modify")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(requestJson))
                        .andDo(new ResultHandler() {
                            @Override
                            public void handle(MvcResult result) throws Exception {
                                System.out.println(result.getResponse().getContentAsString());
                            }
                        }).andExpect(MockMvcResultMatchers.status().isOk());
        //.andExpect(MockMvcResultMatchers.content().string("Hello World!"));
    }

    @Test
    public void testGet()  throws Exception {

        ResultActions actions =
                mvc.perform(MockMvcRequestBuilders.get("/student/get/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                        .andDo(new ResultHandler() {
                            @Override
                            public void handle(MvcResult result) throws Exception {
                                System.out.println(result.getResponse().getContentAsString());
                            }
                        }).andExpect(MockMvcResultMatchers.status().isOk());
        //.andExpect(MockMvcResultMatchers.content().string("Hello World!"));
    }
}
