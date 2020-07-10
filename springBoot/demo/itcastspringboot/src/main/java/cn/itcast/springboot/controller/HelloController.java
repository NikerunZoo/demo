package cn.itcast.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author NikerunZoo
 * @date 2020/7/7 0007 16:34
 * @ResponsBody:返回json数据
 */
@RestController //相当于@Controller+@ResponsBody
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private DataSource dataSource;
//    @RequestMapping("/show")
    @GetMapping("show")
    public String test(){
        return "hello spring boot";
    }

}
