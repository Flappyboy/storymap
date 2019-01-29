package cn.edu.nju.software.storymapping.map.dao;

import java.util.List;

import cn.edu.nju.software.storymapping.map.entity.Image;

public interface ImageMapper {
	public void insert(Image image);

	public void delete(Integer id);
	
	public void update(Image image);
	
	public Image getById(Integer id);

	public List<Image> listAll();

}
