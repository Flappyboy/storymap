package cn.edu.nju.software.storymapping.map.dao;

import java.util.List;

import cn.edu.nju.software.storymapping.map.entity.Release;

public interface ReleaseMapper {
	public void insert(Release Release);

	public void delete(int id);
	
	public void update(Release Release);
	
	public Release getById(int id);

	public List<Release> listAll();

	public List<Release> listByStoryMapId(int id);
}
