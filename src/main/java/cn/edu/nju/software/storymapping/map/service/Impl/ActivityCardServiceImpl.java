package cn.edu.nju.software.storymapping.map.service.Impl;

import java.util.List;

import cn.edu.nju.software.storymapping.map.utils.ReorderUtil;
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

    //添加数据的时候需要重新排序
    @Override
    public void addActivity(ActivityCard activityCard) {
        //首先获取原有的list
        List<ActivityCard> activityCardList = activityCardMapper.getActivityCardByStoryMapId(activityCard.getStoryMapId());
        //通过reorderUtil来对list中的数据进行重排序
        List<ActivityCard> needToUpdate = ReorderUtil.reOrderBeforeInsert(activityCardList, activityCard.getOrder());
        //将order需要更新的activity跟新至数据库中
        for (ActivityCard card : needToUpdate) {
            activityCardMapper.updateOrder(card.getId(), card.getOrder());
        }
        //将数据插入到数据库中
        activityCardMapper.addActivity(activityCard);
    }

    @Override
    public void deleteActivity(Integer id) {

        //获取所在StoryMapId
        Integer storyMapId = activityCardMapper.getStoryMapId(id);
        //删除activity
        activityCardMapper.deleteActivity(id);
        //根据order重新获取同一storyMapId下的activity
        List<ActivityCard> activityCardList = activityCardMapper.getActivityCardByStoryMapId(storyMapId);
        //重排序，从0开始
        List<ActivityCard> needToUpdate = ReorderUtil.reOrderAfterDelete(activityCardList);
        for (ActivityCard activityCard : needToUpdate) {
            activityCardMapper.updateOrder(activityCard.getId(), activityCard.getOrder());
        }
    }

    @Override
    public void updateActivity(ActivityCard activityCard) {
        activityCardMapper.updateActivity(activityCard);

    }

    @Override
    public List<ActivityCard> getActivityCardByStoryMapId(Integer storyMapId) {
        // TODO Auto-generated method stub
        logger.debug("ActivityCardServiceImpl::getActivityCardByStoryMapId storyMapId = {}", storyMapId);
        List<ActivityCard> result = activityCardMapper.getActivityCardByStoryMapId(storyMapId);
        return result;
    }

    public String getActivityOrder(Integer activityId) {
        return activityCardMapper.getActivityOrder(activityId);
    }

}
