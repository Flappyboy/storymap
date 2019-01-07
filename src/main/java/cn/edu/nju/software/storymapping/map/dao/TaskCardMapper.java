package cn.edu.nju.software.storymapping.map.dao;

import cn.edu.nju.software.storymapping.map.entity.TaskCard;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface TaskCardMapper {
    public void updateTaskCard(TaskCard taskCard);

    public void deleteTaskCard(Integer id);

    public void addTaskCard(TaskCard taskCard);

    public List<TaskCard> getTaskCardByActivityId(Integer activityId);

}
