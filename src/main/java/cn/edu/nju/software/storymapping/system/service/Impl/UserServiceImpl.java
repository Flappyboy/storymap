package cn.edu.nju.software.storymapping.system.service.Impl;

import cn.edu.nju.software.storymapping.system.dao.UserMapper;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.system.entity.UserExample;
import cn.edu.nju.software.storymapping.system.service.UserService;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryByUsername(String username) {
        User user = userMapper.selectUser(username);
        return user;

    }

    @Override
    public void setPassword(String username, String password) {
        User user = userMapper.selectUser(username);
        user.setPassword(password);
        userMapper.updateUser(user);
    }

    public int insertUser(User user) {
        //插入user的时候需要初始化一个workspace
        String password = user.getPassword();
        String encodePassword = encoder.encode(password);
        user.setPassword(encodePassword);
        int result = userMapper.insertUser(user);
        return result;
    }

    @Override
    public void update(User user) {
        if (user.getPassword() != null) {
            //加密
            String encodePassword = encoder.encode(user.getPassword());
            user.setPassword(encodePassword);
        }
        userMapper.updateUser(user);
    }

    @Override
    public User currentUser() {
        return queryByUsername(UserUtil.currentUserName());
    }
}
