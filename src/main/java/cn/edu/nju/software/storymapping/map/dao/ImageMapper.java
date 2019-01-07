package cn.edu.nju.software.storymapping.map.dao;

import java.util.List;

import cn.edu.nju.software.storymapping.map.entity.Image;

public interface ImageMapper {
	public void insert(Image Image);

	public void delete(int id);
	
	public void update(Image Release);
	
	public Image getById(int id);

	public List<Image> listAll();

}
