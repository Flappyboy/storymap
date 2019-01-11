package cn.edu.nju.software.storymapping.map.dao;

import cn.edu.nju.software.storymapping.map.entity.ActivityCard;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActivityCardMapper {
    //增加
    public void addActivity(ActivityCard activityCard);

    //删除
    public void deleteActivity(Integer id);

    //改
    public void updateActivity(ActivityCard activityCard);

    public List<ActivityCard> getActivityCardByStoryMapId(Integer storyMapId);

}
