package cn.edu.nju.software.storymapping;

import java.util.Date;
import java.util.List;

import cn.edu.nju.software.storymapping.system.dao.UserMapper;
import cn.edu.nju.software.storymapping.system.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.edu.nju.software.storymapping.map.dao.ReleaseMapper;
import cn.edu.nju.software.storymapping.map.dao.StoryMapMapper;
import cn.edu.nju.software.storymapping.map.entity.ActivityCard;
import cn.edu.nju.software.storymapping.map.entity.Release;
import cn.edu.nju.software.storymapping.map.entity.StoryMap;
import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;
import cn.edu.nju.software.storymapping.map.service.ActivityCardService;
import cn.edu.nju.software.storymapping.map.service.ImageService;
import cn.edu.nju.software.storymapping.map.service.ReleaseService;
import cn.edu.nju.software.storymapping.map.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorymappingApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUsername("jinsiye");
        user.setPassword("123456");
        userMapper.insertUser(user);
        System.out.println(user.getId());
    }
}
