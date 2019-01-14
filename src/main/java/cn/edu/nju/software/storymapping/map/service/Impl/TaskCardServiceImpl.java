package cn.edu.nju.software.storymapping.map.service.Impl;

import cn.edu.nju.software.storymapping.map.dao.TaskCardMapper;
import cn.edu.nju.software.storymapping.map.entity.TaskCard;
import cn.edu.nju.software.storymapping.map.utils.ReorderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.service.TaskCardService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskCardServiceImpl implements TaskCardService {
    @Autowired
    private TaskCardMapper taskCardMapper;

    public List<TaskCard> getTaskCardByActivityId(Integer activityId) {
        List<TaskCard> list = taskCardMapper.getTaskCardByActivityId(activityId);
        if (list == null)
            return new ArrayList<>();
        return list;
    }

    //更新taskcard
    public void updateTaskCard(TaskCard taskCard) {
        taskCardMapper.updateTaskCard(taskCard);
    }

    //删除,删除之后需要进行重排序
    public void deleteById(Integer taskCardId, Integer activityId) {

        taskCardMapper.deleteTaskCard(taskCardId);
        //将对象从数据库中删除
        List<TaskCard> taskCardList = getTaskCardByActivityId(activityId);
        //将指定的对象从list中删除，并将他们重新排序
        ReorderUtil.reOrder(taskCardList);
        //更新order
        for (TaskCard taskCard : taskCardList) {
            taskCardMapper.updateOrder(taskCard.getOrder(), taskCard.getId());
        }

    }

    //创建一个taskCard
    public void createTaskCard(TaskCard taskCard) {

        taskCardMapper.addTaskCard(taskCard);
    }

}
