package cn.edu.nju.software.storymapping.map.service.Impl;

import cn.edu.nju.software.storymapping.map.dao.StoryMapMapper;
import cn.edu.nju.software.storymapping.map.entity.StoryMap;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.service.StoryMapService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryMapServiceImpl implements StoryMapService {
    @Autowired
    private StoryMapMapper storyMapMapper;

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
        //
    }

    //更新StoryMap
    public void updateStoryMap(StoryMap storyMap) {
        storyMapMapper.update(storyMap);
    }


}
