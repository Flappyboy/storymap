package cn.edu.nju.software.storymapping.utils;

import cn.edu.nju.software.storymapping.system.entity.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static String currentUserName(){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
