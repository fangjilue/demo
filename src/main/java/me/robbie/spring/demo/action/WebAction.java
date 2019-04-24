package me.robbie.spring.demo.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-04-22 21:46
 * @since [产品/模块版本]
 */
@Controller
public class WebAction {



    @RequestMapping(value = "/web",method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("date",new Date().toInstant());
        return "index@layout/layout";
    }

    @RequestMapping(value = "/web/user",method = RequestMethod.GET)
    public String userList(Model model) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("zhangzq");
        arr.add("licm");

        model.addAttribute("arr",arr);
        return "module/user_list@layout/layout";
    }


    @RequestMapping(value = "/web/user1",method = RequestMethod.GET)
    public String userList2(Model model) {
        Integer i = null;
        i.toString();
        return "module/user_list@layout/layout";
    }
}
