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

    @Override
    public String getTaskOrder(Integer taskId) {
        return taskCardMapper.getTaskOrder(taskId);
    }


    //更新taskcard
    public void updateTaskCard(TaskCard taskCard) {
        taskCardMapper.updateTaskCard(taskCard);
    }

    //删除,删除之后需要进行重排序
    @Override
    public void deleteById(Integer taskCardId) {

        //将对象从数据库中删除
        Integer activityCardId = taskCardMapper.getActivityCardId(taskCardId);
        taskCardMapper.deleteTaskCard(taskCardId);
        List<TaskCard> taskCardList = getTaskCardByActivityId(activityCardId);
        //将指定的对象从list中删除，并将他们重新排序
        List<TaskCard> needToUpdate = ReorderUtil.reOrderAfterDelete(taskCardList);
        //更新order
        for (TaskCard taskCard : needToUpdate) {
            taskCardMapper.updateOrder(taskCard.getOrder(), taskCard.getId());
        }

    }

    //创建一个taskCard
    public void createTaskCard(TaskCard taskCard) {
        List<TaskCard> taskCardList = taskCardMapper.getTaskCardByActivityId(taskCard.getActivityId());
        List<TaskCard> needToUpdate = ReorderUtil.reOrderBeforeInsert(taskCardList, taskCard.getOrder());
        for (TaskCard item : needToUpdate) {
            taskCardMapper.updateOrder(item.getOrder(), item.getId());
        }
        taskCardMapper.addTaskCard(taskCard);
    }

    @Override
    public boolean move(TaskCard taskCard) {
        //获取获取id，获取最新移动到的位置的id
        Integer id = taskCard.getId();
        Integer activityCardId = taskCardMapper.getActivityCardId(id);
        Integer newActivityId = taskCard.getActivityId();
        List<TaskCard> newNeedToUpdate = getTaskCardByActivityId(newActivityId);
        List<TaskCard> originNeedToUpdate = getTaskCardByActivityId(activityCardId);
        if (activityCardId.equals(newActivityId)) {
            for (int i = 0; i < originNeedToUpdate.size(); i++) {
                if (originNeedToUpdate.get(i).getId().equals(id))
                    originNeedToUpdate.remove(i);
            }

        }
        //更新activityCardId
        taskCardMapper.updateTaskCard(taskCard);
        //将原来activityCardId下的task重排序
        originNeedToUpdate = ReorderUtil.reOrderAfterDelete(originNeedToUpdate);
        for (TaskCard card : originNeedToUpdate) {
            taskCardMapper.updateTaskCard(card);
        }
        if (activityCardId.equals(newActivityId)) {
            newNeedToUpdate = getTaskCardByActivityId(newActivityId);
        }
        newNeedToUpdate = ReorderUtil.reOrderBeforeInsert(newNeedToUpdate, taskCard.getOrder(), id);
        for (TaskCard card : newNeedToUpdate) {
            taskCardMapper.updateTaskCard(card);
        }
        return true;

    }


}
