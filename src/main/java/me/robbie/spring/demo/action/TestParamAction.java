package me.robbie.spring.demo.action;

import me.robbie.spring.demo.model.Student;
import me.robbie.spring.demo.response.RobbieResponse;
import me.robbie.spring.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-03-01 11:52
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/test")
public class TestParamAction {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public RobbieResponse<Integer> save(@RequestBody Student student){

        Integer id = studentService.save(student);

        return RobbieResponse.bulidSuccessResponseBody(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RobbieResponse<Integer> add(Student student){

        Integer id = studentService.save(student);

        return RobbieResponse.bulidSuccessResponseBody(id);
    }


    @RequestMapping(value = "modify", method = RequestMethod.PUT)
    public RobbieResponse<Void> modify(@RequestBody Student student){

        studentService.modify(student);

        return RobbieResponse.bulidSuccessResponseBody(null);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public RobbieResponse<Student> get(@PathVariable Integer id){
        Student student = studentService.get(id);

        return RobbieResponse.bulidSuccessResponseBody(student);
    }


    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RobbieResponse<Student> query(@RequestParam Integer id){
        Student student = studentService.get(id);

        return RobbieResponse.bulidSuccessResponseBody(student);
    }

}
