package cn.edu.nju.software.storymapping.system.controller;

import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.system.service.UserService;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public User GetUser(){
        //MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.queryByUsername(UserUtil.currentUserName());
    }

    /**
     * 个人中心
     */
    @GetMapping("/index")
    public String index() {
        return "user/index";
    }

}
