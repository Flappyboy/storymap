package cn.edu.nju.software.storymapping.map.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.dao.ActivityCardMapper;
import cn.edu.nju.software.storymapping.map.entity.ActivityCard;
import cn.edu.nju.software.storymapping.map.service.ActivityCardService;

@Service
public class ActivityCardServiceImpl implements ActivityCardService {
	private static final Logger logger = LoggerFactory.getLogger(ActivityCardServiceImpl.class);
	@Autowired
	private ActivityCardMapper activityCardMapper;

	@Override
	public void addActivity(ActivityCard activityCard) {
		// TODO Auto-generated method stub
		logger.debug("ActivityCardServiceImpl::addActivity activityCard = {}", activityCard.toString());
		activityCardMapper.addActivity(activityCard);
		logger.debug("ActivityCardServiceImpl::addActivity id = {}", activityCard.getId());
	}

	@Override
	public void deleteActivity(Integer id) {
		logger.debug("ActivityCardServiceImpl::deleteActivity id = {}", id);
		activityCardMapper.deleteActivity(id);

	}

	@Override
	public void updateActivity(ActivityCard activityCard) {
		logger.debug("ActivityCardServiceImpl::updateActivity activityCard = {}", activityCard.toString());
		activityCardMapper.updateActivity(activityCard);

	}

	@Override
	public List<ActivityCard> getActivityCardByStoryMapId(Integer storyMapId) {
		// TODO Auto-generated method stub
		logger.debug("ActivityCardServiceImpl::getActivityCardByStoryMapId storyMapId = {}", storyMapId);
		List<ActivityCard> result = activityCardMapper.getActivityCardByStoryMapId(storyMapId);
		return result;
	}

}
