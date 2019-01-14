package cn.edu.nju.software.storymapping.map.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.dao.ReleaseMapper;
import cn.edu.nju.software.storymapping.map.entity.Release;
import cn.edu.nju.software.storymapping.map.service.ReleaseService;

@Service
public class ReleaseServiceImpl implements ReleaseService {
	private static final Logger logger = LoggerFactory.getLogger(ReleaseServiceImpl.class);
	@Autowired
	private ReleaseMapper releaseMapper;

	@Override
	public void addRelease(Release release) {
		// TODO Auto-generated method stub
		logger.debug("ReleaseServiceImpl::addRelease Release = {}", release.toString());
		releaseMapper.insert(release);
		logger.debug("ReleaseServiceImpl::addRelease id = {}", release.getId());
	}

	@Override
	public void deleteRelease(Integer id) {
		// TODO Auto-generated method stub
		logger.debug("ReleaseServiceImpl::deleteRelease id = {}", id);
		releaseMapper.delete(id);
	}

	@Override
	public void updateRelease(Release release) {
		// TODO Auto-generated method stub
		logger.debug("ReleaseServiceImpl::updateRelease Release = {}", release.toString());
		releaseMapper.update(release);
	}

	@Override
	public Release getReleaseById(Integer id) {
		// TODO Auto-generated method stub
		logger.debug("ReleaseServiceImpl::getReleaseById id = {}", id);
		Release release = releaseMapper.getById(id);
		return release;
	}

	@Override
	public List<Release> listAll() {
		// TODO Auto-generated method stub
		List<Release> result = releaseMapper.listAll();
		return result;
	}

	@Override
	public List<Release> listReleaseByStoryMapId(int id) {
		// TODO Auto-generated method stub
		logger.debug("ReleaseServiceImpl::listReleaseByStoryMapId id = {}", id);
		List<Release> result = releaseMapper.listByStoryMapId(id);
		return result;
	}

}
