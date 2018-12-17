package cn.edu.nju.software.storymapping.system.service.Impl;

import cn.edu.nju.software.storymapping.system.dao.UserMapper;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.system.entity.UserExample;
import cn.edu.nju.software.storymapping.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User queryByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> userList= userMapper.selectByExample(new UserExample());
        if(userList.size()>0)
            return userList.get(0);
        else
            return null;
    }

    @Override
    public void setPassword(String username, String password) {
        User user = new User();
        user.setPassword(password);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        userMapper.updateByExampleSelective(user, userExample);
    }
}
