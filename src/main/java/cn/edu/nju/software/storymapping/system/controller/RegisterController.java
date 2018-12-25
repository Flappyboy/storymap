package cn.edu.nju.software.storymapping.system.controller;


import cn.edu.nju.software.storymapping.map.entity.User;
import cn.edu.nju.software.storymapping.system.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class RegisterController {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/register")
    public String register(User user) {
        user.setCreateTime(new Date());
        return "";
    }

}
