package cn.edu.nju.software.storymapping.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TouristsModeController {

    @RequestMapping(value = "/touristsmode/{page}.html", method = RequestMethod.GET)
    public ModelAndView touristsmode1(@PathVariable String page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(page);
        return modelAndView;
    }
    @RequestMapping(value = "/touristsmode/{path1}/{page}.html", method = RequestMethod.GET)
    public ModelAndView touristsmode2(@PathVariable String path1, @PathVariable String page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path1+"/"+page);
        return modelAndView;
    }
    @RequestMapping(value = "/touristsmode/{path1}/{path2}/{page}.html", method = RequestMethod.GET)
    public ModelAndView touristsmode3(@PathVariable String path1,@PathVariable String path2,@PathVariable String page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path1+"/"+path2+"/"+page);
        return modelAndView;
    }

}
