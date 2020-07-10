package cn.itcast.user.controller;

import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author NikerunZoo
 * @date 2020/7/7 0007 19:16
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("test")
    @ResponseBody
    public String test(){
        return "hellp user";

    }

    @GetMapping("{id}")
    @ResponseBody
    public User queryUserById(@PathVariable("id") Integer id){
        return this.userService.queryUserById(id);
    }
}
