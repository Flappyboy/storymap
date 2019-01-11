package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mock")
public class MockController {

    @GetMapping(value = "/user")
    public User getUser(){
        User user = UserUtil.currentUser();
        return user;
    }
}
