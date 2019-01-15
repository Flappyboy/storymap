package cn.edu.nju.software.storymapping.map.service;

import java.util.List;

import cn.edu.nju.software.storymapping.map.entity.ActivityCard;

public interface ActivityCardService {
    void addActivity(ActivityCard activityCard);

    void deleteActivity(Integer id);

    public void updateActivity(ActivityCard activityCard);

    public List<ActivityCard> getActivityCardByStoryMapId(Integer storyMapId);
}
