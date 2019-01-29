package cn.edu.nju.software.storymapping.system.controller;

import cn.edu.nju.software.storymapping.map.entity.Workspace;
import cn.edu.nju.software.storymapping.map.service.WorkspaceService;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.system.service.UserService;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TemplateController {
    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("workspaces");
        setModel(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/template/{page}.html", method = RequestMethod.GET)
    public ModelAndView template(@PathVariable String page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(page);
        setModel(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/template/{path1}/{page}.html", method = RequestMethod.GET)
    public ModelAndView template1(@PathVariable String path1, @PathVariable String page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path1 + "/" + page);
        setModel(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/template/{path1}/{path2}/{page}.html", method = RequestMethod.GET)
    public ModelAndView template2(@PathVariable String path1, @PathVariable String path2, @PathVariable String page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path1 + "/" + path2 + "/" + page);
        setModel(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/template/{path1}/{path2}/{path3}/{page}.html", method = RequestMethod.GET)
    public ModelAndView template3(@PathVariable String path1, @PathVariable String path2, @PathVariable String path3, @PathVariable String page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path1 + "/" + path2 + "/" + path3 + "/" + page);
        setModel(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/template/{path1}/{path2}/{path3}/{path4}/{page}.html", method = RequestMethod.GET)
    public ModelAndView template4(@PathVariable String path1, @PathVariable String path2, @PathVariable String path3, @PathVariable String path4, @PathVariable String page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path1 + "/" + path2 + "/" + path3 + "/" + path4 + "/" + page);
        setModel(modelAndView);
        return modelAndView;
    }

    private void setModel(ModelAndView modelAndView) {
        User user = userService.currentUser();
        if (user != null) {
            List<Workspace> workspaceList = workspaceService.getWorkSpaceById(user.getId());
            modelAndView.addObject("username", user.getUsername());
            modelAndView.addObject("phone", user.getPhone());
            modelAndView.addObject("email", user.getEmail());
            modelAndView.addObject("workspaceList", workspaceList);
        }
    }
}
