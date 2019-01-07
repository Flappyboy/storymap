package cn.edu.nju.software.storymapping.map.dao;

import java.util.List;

import cn.edu.nju.software.storymapping.map.entity.StoryMap;

public interface StoryMapMapper {
	public void insert(StoryMap StoryMap);

	public StoryMap getById(int id);

	public void update(StoryMap StoryMap);
	
	public void delete(int id);

	public List<StoryMap> listAll();
	
	public List<StoryMap> listByWorkSpaceId(int id);
}
