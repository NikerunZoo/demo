package cn.itcast.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NikerunZoo
 * @date 2020/7/7 0007 16:34
 * @ResponsBody:返回json数据
 */
@RestController //相当于@Controller+@ResponsBody
@RequestMapping("/hello2")
public class Hello2Controller {

//    @RequestMapping("/show")
    @GetMapping("show")
    public String test(){
        return "hello2 spring boot";
    }


}
