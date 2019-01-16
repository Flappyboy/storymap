package cn.edu.nju.software.storymapping;

import java.util.Date;
import java.util.List;

import cn.edu.nju.software.storymapping.map.service.*;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorymappingApplicationTests {

    @Autowired
    ActivityCardService activityCardService;

    @Autowired
    SubTaskCardService subTaskCardService;

    @Autowired
    TaskCardService taskCardService;

    @Test
    public void contextLoads() {
        subTaskCardService.getSubTaskCardByTaskIdAndReleaseId(95,13);
    }


}
