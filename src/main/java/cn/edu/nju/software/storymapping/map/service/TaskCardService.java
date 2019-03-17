package cn.edu.nju.software.storymapping.map.service;

import cn.edu.nju.software.storymapping.map.entity.TaskCard;

import java.util.List;

public interface TaskCardService {
    public void deleteById(Integer taskCardId);

    public void createTaskCard(TaskCard taskCard);

    public void updateTaskCard(TaskCard taskCard);

    public List<TaskCard> getTaskCardByActivityId(Integer activityId);

    public String getTaskOrder(Integer taskId);

    public boolean move(TaskCard taskCard);

}
