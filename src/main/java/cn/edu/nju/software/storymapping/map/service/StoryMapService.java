package cn.edu.nju.software.storymapping.map.service;

import cn.edu.nju.software.storymapping.map.entity.StoryMap;

import java.util.List;

public interface StoryMapService {
    public List<StoryMap> getStoryMapByUserId(Integer userId);

    public List<StoryMap> getStoryMapByWorkSpaceId(Integer workSpaceId);

    public StoryMap getStoryMapById(Integer id);

    public void deleteStoryMapById(Integer id);

    public void createStoryMap(StoryMap storyMap);

    public void updateStoryMap(StoryMap storyMap);


}
