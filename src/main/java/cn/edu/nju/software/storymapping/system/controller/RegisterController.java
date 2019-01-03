package cn.edu.nju.software.storymapping.system.controller;


import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Response register(User user) {
        if(StringUtils.isEmpty(user.getUsername())){
            return Response.createDefaultResponse().fail("error");
        }
        user.setCreateTime(new Date());
        userService.insertUser(user);
        return Response.createDefaultResponse().success("success");
    }

    @GetMapping("/validation")
    public String validation(String username) {
        if (username == null || "".equals(username.trim()))
            return "error";
        User user = userService.queryByUsername(username);
        if (user == null) return "error";

        return "ok";
    }

}
