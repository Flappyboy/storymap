package cn.edu.nju.software.storymapping.controller.sys;

import cn.edu.nju.software.storymapping.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public String GetUser(){
        return userService.queryall().get(0).toString();
    }
}
