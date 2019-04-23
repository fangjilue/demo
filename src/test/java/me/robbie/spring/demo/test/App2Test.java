package me.robbie.spring.demo.test;

import me.robbie.spring.demo.Run;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * 远程测试controller
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Run.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class App2Test {

    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     */
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);

    }

    @Test
    public void testIndex() throws Exception{
        ResponseEntity<String> entity = restTemplate.getForEntity(this.base.toString() +"/",String.class);
        System.out.println(entity.getBody());
    }


    @Test
    public void testDoNot() throws Exception{
        ResponseEntity<String> entity = restTemplate.getForEntity(this.base.toString() +"/student/get/1",String.class);
        System.out.println(entity.getBody());
    }
}
