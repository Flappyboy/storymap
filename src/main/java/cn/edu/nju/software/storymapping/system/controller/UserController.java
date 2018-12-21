package cn.edu.nju.software.storymapping.system.controller;

import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.system.service.UserService;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public User GetUser(){
        //MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.queryByUsername(UserUtil.currentUserName());
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    public String findById(@PathVariable Long id) {
        return "";
    }

    /**
     * 个人中心
     */
    @GetMapping("/index")
    public String index() {
        return "user/index";
    }

}
