package cn.edu.nju.software.storymapping.utils;

import cn.edu.nju.software.storymapping.system.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static String currentUserName(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
    public static User currentUser(){
        User user = null;
        try{
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch(Exception e){
            return null;
        }
        return user;
    }
}
