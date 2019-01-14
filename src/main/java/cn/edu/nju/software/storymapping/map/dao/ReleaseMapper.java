package cn.edu.nju.software.storymapping.map.dao;

import java.util.List;

import cn.edu.nju.software.storymapping.map.entity.Release;

public interface ReleaseMapper {
	public void insert(Release release);

	public void delete(Integer id);
	
	public void update(Release release);
	
	public Release getById(Integer id);

	public List<Release> listAll();

	public List<Release> listByStoryMapId(Integer id);
}
