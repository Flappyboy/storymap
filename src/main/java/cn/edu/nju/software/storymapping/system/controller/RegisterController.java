package cn.edu.nju.software.storymapping.system.controller;


import cn.edu.nju.software.storymapping.map.entity.Workspace;
import cn.edu.nju.software.storymapping.map.service.WorkspaceService;
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

    @Autowired
    private WorkspaceService workspaceService;

    @PostMapping("/register")
    public Response register(User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            return Response.createDefaultResponse().fail("用户名不能为空！");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return Response.createDefaultResponse().fail("密码不能为空！");
        }
        if (containUser(user.getUsername())) {
            return Response.createDefaultResponse().fail("用户名已存在！");
        }
        //创建角色
        user.setCreateTime(new Date());
        //插入角色
        userService.insertUser(user);
        //初始化一个workspace
        Workspace workspace = new Workspace();
        initWorkSpace(workspace, user.getId());
        workspaceService.CreateWorkSapce(workspace);
        return Response.createDefaultResponse().success("注册成功！");
    }

    @GetMapping("/validation")
    public Response validation(String username) {
        if (username == null || "".equals(username.trim()))
            return Response.createDefaultResponse().fail("用户名不能为空！");
        if (containUser(username)) {
            return Response.createDefaultResponse().fail("用户名已存在！");
        }
        return Response.createDefaultResponse().success("用户名可用");
    }

    public boolean containUser(String username) {
        User user = userService.queryByUsername(username);
        return user == null ? false : true;
    }

    public void initWorkSpace(Workspace workspace, Integer userId) {
        workspace.setDescription("default");
        workspace.setCreateTime(new Date());
        workspace.setName("default workspace");
        workspace.setUserId(userId);
    }

}
