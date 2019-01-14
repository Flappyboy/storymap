package cn.edu.nju.software.storymapping;

import java.util.Date;
import java.util.List;

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
	private ActivityCardService activityCardService;

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ReleaseService releaseService;
	
	@Autowired
	private RoleService roleService;

	@Test
	public void contextLoads() {
		System.out.println("-------------------start--------------");
		ActivityCard activityCard=new ActivityCard();
		activityCard.setColor("green");
		activityCard.setCreateTime(new Date());
		activityCard.setCreatorId(1);
		activityCard.setDescription("sss");
		activityCard.setName("test1");
		activityCard.setOrder("3");
		activityCard.setStoryMapId(2);
		activityCard.setId(6);
		
		
		List<ActivityCard> r=activityCardService.getActivityCardByStoryMapId(2);
		for (ActivityCard activityCard2 : r) {
			System.out.println(activityCard2.toString());
		}

		System.out.println("-------------------end--------------");

	}
}
