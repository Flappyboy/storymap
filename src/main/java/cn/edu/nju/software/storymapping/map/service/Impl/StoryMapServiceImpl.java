package cn.edu.nju.software.storymapping.map.service.Impl;

import cn.edu.nju.software.storymapping.map.dao.StoryMapMapper;
import cn.edu.nju.software.storymapping.map.entity.ActivityCard;
import cn.edu.nju.software.storymapping.map.entity.Release;
import cn.edu.nju.software.storymapping.map.entity.StoryMap;
import cn.edu.nju.software.storymapping.map.service.ActivityCardService;
import cn.edu.nju.software.storymapping.map.service.ReleaseService;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.service.StoryMapService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StoryMapServiceImpl implements StoryMapService {
    @Autowired
    private StoryMapMapper storyMapMapper;

    @Autowired
    private ActivityCardService activityCardService;

    @Autowired
    private ReleaseService releaseService;


    //根据用户ID来获取list
    public List<StoryMap> getStoryMapByUserId(Integer userId) {
        List<StoryMap> storyMapList = storyMapMapper.listByUserId(userId);
        if (storyMapList == null)
            return new ArrayList<>();
        return storyMapList;
    }

    //根据workSpaceId来获取list
    public List<StoryMap> getStoryMapByWorkSpaceId(Integer workSpaceId) {
        List<StoryMap> storyMapList = storyMapMapper.listByWorkSpaceId(workSpaceId);
        if (storyMapList == null)
            return new ArrayList<>();
        return storyMapList;
    }

    //根据storyMapId获取storyMap
    public StoryMap getStoryMapById(Integer id) {
        StoryMap storyMap = storyMapMapper.getById(id);
        return storyMap;
    }

    //根据Id删除storyMap
    public void deleteStoryMapById(Integer id) {
        storyMapMapper.delete(id);
    }

    //创建storyMap
    public void createStoryMap(StoryMap storyMap) {
        //创建一个storymap
        storyMapMapper.insert(storyMap);
        //记录user和storyMap一一对应的关系，创建者的权限就是""
        storyMapMapper.addUserStoryMapReleation(storyMap.getUserId(), storyMap.getId(), StoryMap.AUTHORITY_READ_WRITE);

        //StoryMap中需要创建一个activity
        ActivityCard activityCard = new ActivityCard();
        activityCard.setName("Default Activity Card");
        activityCard.setCreateTime(new Date());
        activityCard.setCreatorId(storyMap.getUserId());
        activityCard.setStoryMapId(storyMap.getId());
        activityCard.setOrder("0");
        System.out.println(activityCard);
        activityCardService.addActivity(activityCard);
        System.out.println(activityCard);

        Release release = new Release();
        release.setCreateTime(new Date());
        release.setCreatorId(storyMap.getUserId());
        release.setName("Release 1");
        release.setOrder("0");
        release.setStoryMapId(storyMap.getId());
        releaseService.addRelease(release);
    }

    //更新StoryMap
    public void updateStoryMap(StoryMap storyMap) {
        storyMapMapper.update(storyMap);
    }

    @Override
    public String getAuthorityForStorymap(Integer userId, Integer storyMapId) {
        return storyMapMapper.getAuthorityForStorymap(userId, storyMapId);
    }


}
