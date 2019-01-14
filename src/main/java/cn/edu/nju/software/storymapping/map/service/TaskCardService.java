package cn.edu.nju.software.storymapping.map.service;

import cn.edu.nju.software.storymapping.map.entity.TaskCard;

import java.util.List;

public interface TaskCardService {
    public void deleteById(Integer taskCardId, Integer activityId);

    public void createTaskCard(TaskCard taskCard);

    public void updateTaskCard(TaskCard taskCard);

    public List<TaskCard> getTaskCardByActivityId(Integer activityId);

}
