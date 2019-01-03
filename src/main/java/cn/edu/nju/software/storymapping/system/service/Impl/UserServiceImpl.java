package cn.edu.nju.software.storymapping.system.service.Impl;

import cn.edu.nju.software.storymapping.system.dao.UserMapper;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.system.entity.UserExample;
import cn.edu.nju.software.storymapping.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryByUsername(String username) {
        User user = userMapper.selectUser(username);
        System.out.println(user);
        return user;

    }

    @Override
    public void setPassword(String username, String password) {
        User user = userMapper.selectUser(username);
        user.setPassword(password);
        userMapper.updateUser(user);
    }

    public int insertUser(User user) {
        int result = userMapper.insertUser(user);
        return result;
    }
}
