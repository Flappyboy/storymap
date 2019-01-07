package cn.edu.nju.software.storymapping;

import cn.edu.nju.software.storymapping.map.dao.ActivityCardMapper;
import cn.edu.nju.software.storymapping.map.dao.SubTaskCardMapper;
import cn.edu.nju.software.storymapping.map.dao.TaskCardMapper;
import cn.edu.nju.software.storymapping.map.entity.ActivityCard;
import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;
import cn.edu.nju.software.storymapping.system.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorymappingApplicationTests {

    @Autowired
    private ActivityCardMapper activityCardMapper;

    @Test
    public void contextLoads() {
        System.out.println(activityCardMapper.getActivityCardByStoryMapId(1));
    }
}
