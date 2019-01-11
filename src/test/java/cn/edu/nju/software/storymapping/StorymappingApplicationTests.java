package cn.edu.nju.software.storymapping;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.edu.nju.software.storymapping.map.dao.ReleaseMapper;
import cn.edu.nju.software.storymapping.map.dao.StoryMapMapper;
import cn.edu.nju.software.storymapping.map.entity.Release;
import cn.edu.nju.software.storymapping.map.entity.StoryMap;
import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorymappingApplicationTests {

	@Autowired
	private StoryMapMapper storyMapMapper;

	@Autowired
	private ReleaseMapper releaseMapper;

	@Test
	public void contextLoads() {
		System.out.println("-------------------start--------------");
		StoryMap sm = storyMapMapper.getById(1);
		List<Release> listR = sm.getReleaseList();
		System.out.println(listR.size());
		for (Release release : listR) {
			List<SubTaskCard> listS=release.getSubTaskCardList();
			for (SubTaskCard subTaskCard : listS) {
				System.out.println(subTaskCard.toString());
			}
			System.out.println();
		}

		System.out.println("-------------------end--------------");

	}
}
