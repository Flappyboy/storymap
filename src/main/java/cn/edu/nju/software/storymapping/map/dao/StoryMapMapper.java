package cn.edu.nju.software.storymapping.map.dao;

import java.util.List;

import cn.edu.nju.software.storymapping.map.entity.StoryMap;

public interface StoryMapMapper {
    public void insert(StoryMap storyMap);

    public StoryMap getById(Integer id);

    public void update(StoryMap storyMap);

    public void delete(Integer id);

    public List<StoryMap> listAll();

    public List<StoryMap> listByWorkSpaceId(Integer id);

    public List<StoryMap> listByUserId(Integer userId);
}
