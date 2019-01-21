package cn.edu.nju.software.storymapping.system.controller;


import cn.edu.nju.software.storymapping.map.entity.ActivityCard;
import cn.edu.nju.software.storymapping.map.entity.StoryMap;
import cn.edu.nju.software.storymapping.map.entity.Workspace;
import cn.edu.nju.software.storymapping.map.service.ActivityCardService;
import cn.edu.nju.software.storymapping.map.service.StoryMapService;
import cn.edu.nju.software.storymapping.map.service.WorkspaceService;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private StoryMapService storyMapService;

    @Autowired
    private ActivityCardService activityCardService;

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(User user, ModelAndView modelAndView) {
        String error=null;
        if (StringUtils.isEmpty(user.getUsername())) {
            error="用户名不能为空！";
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            error="密码不能为空！";
        }
        if (containUser(user.getUsername())) {
            error="用户名已存在！";
        }
        if(error!=null) {
            modelAndView.setViewName("register");
            modelAndView.addObject("error",error);
            return modelAndView;
        }
        //创建角色
        user.setCreateTime(new Date());
        //插入角色
        userService.insertUser(user);
        //初始化一个workspace
        initWorkSpace(user.getId());
        modelAndView.setViewName("login");
        modelAndView.addObject("register","success");
        return modelAndView;
    }

    @GetMapping("/pub/api/validation")
    @ResponseBody
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

    public void initWorkSpace(Integer userId) {
        Workspace workspace = new Workspace();
        workspace.setDescription("default");
        workspace.setCreateTime(new Date());
        workspace.setName("default workspace");
        workspace.setUserId(userId);
        //创建workspace
        workspaceService.CreateWorkSapce(workspace);
        //在workspace里面需要创建一个storyMap
        StoryMap storyMap = new StoryMap();
        storyMap.setWorkSpaceId(workspace.getId());
        storyMap.setUserId(userId);
        storyMapService.createStoryMap(storyMap);
        //StoryMap中需要创建一个activity
        ActivityCard activityCard = new ActivityCard();
        activityCard.setCreateTime(new Date());
        activityCard.setCreatorId(userId);
        activityCard.setStoryMapId(storyMap.getId());
        activityCard.setOrder("0");
        activityCardService.addActivity(activityCard);
    }

}
