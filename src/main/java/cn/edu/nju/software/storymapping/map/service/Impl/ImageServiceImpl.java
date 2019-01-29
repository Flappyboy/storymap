package cn.edu.nju.software.storymapping.map.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.dao.ImageMapper;
import cn.edu.nju.software.storymapping.map.entity.Image;
import cn.edu.nju.software.storymapping.map.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);
	@Autowired
	private ImageMapper imageMapper;

	@Override
	public void addImage(Image image) {
		// TODO Auto-generated method stub
		logger.debug("ImageServiceImpl::addImage Image = {}", image.toString());
		imageMapper.insert(image);
		logger.debug("ImageServiceImpl::addImage id = {}", image.getId());
	}

	@Override
	public void deleteImage(Integer id) {
		// TODO Auto-generated method stub
		logger.debug("ImageServiceImpl::deleteImage id = {}", id);
		imageMapper.delete(id);
	}

	@Override
	public void updateImage(Image image) {
		// TODO Auto-generated method stub
		logger.debug("ImageServiceImpl::updateImage Image = {}", image.toString());
		imageMapper.update(image);
	}

	@Override
	public Image getImageById(Integer id) {
		// TODO Auto-generated method stub
		logger.debug("ImageServiceImpl::getImageById id = {}", id);
		Image image = imageMapper.getById(id);
		return image;
	}

	@Override
	public List<Image> listAll() {
		// TODO Auto-generated method stub
		List<Image> result = imageMapper.listAll();
		return result;
	}

}
