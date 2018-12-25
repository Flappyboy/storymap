package cn.edu.nju.software.storymapping.system.service;

import cn.edu.nju.software.storymapping.system.entity.User;

import java.util.List;

public interface UserService {
    User queryByUsername(String username);

    void setPassword(String username, String password);

    int insertUser(User user);
}
