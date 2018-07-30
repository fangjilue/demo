package me.robbie.spring.demo.action;

import me.robbie.spring.demo.model.Student;
import me.robbie.spring.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/30 下午2:20
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/student")
public class StudentAction {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> save(@RequestBody Student student){

        Integer id = studentService.save(student);

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }


    @RequestMapping(value = "modify", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> modify(@RequestBody Student student){

        studentService.modify(student);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Student> get(@PathVariable Integer id){
        Student student = studentService.get(id);

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}
