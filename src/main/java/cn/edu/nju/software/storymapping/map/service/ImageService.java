package cn.edu.nju.software.storymapping.map.service;

import java.util.List;

import cn.edu.nju.software.storymapping.map.entity.Image;

public interface ImageService {
	void addImage(Image Image);

	void deleteImage(Integer id);
	
	void updateImage(Image Release);
	
	Image getImageById(Integer id);

	List<Image> listAll();
}
