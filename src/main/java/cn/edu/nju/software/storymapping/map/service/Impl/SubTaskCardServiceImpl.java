package cn.edu.nju.software.storymapping.map.service.Impl;

import cn.edu.nju.software.storymapping.map.dao.SubTaskCardMapper;
import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;
import cn.edu.nju.software.storymapping.map.utils.ReorderUtil;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.service.SubTaskCardService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubTaskCardServiceImpl implements SubTaskCardService {
    @Autowired
    private SubTaskCardMapper subTaskCardMapper;

    //根据taskID获取List
    public List<SubTaskCard> getSubTaskCardByTaskIdAndReleaseId(Integer taskId, Integer releaseId) {
        List<SubTaskCard> list = subTaskCardMapper.getSubTaskCardListByTaskIdAndReleaseId(taskId, releaseId);
        if (list == null)
            return new ArrayList<>();
        return list;
    }

    public List<SubTaskCard> getSubTaskCardByTaskIdAndReleaseId(Integer taskId) {
        List<SubTaskCard> list = subTaskCardMapper.getSubTaskCardListByTaskId(taskId);
        if (list == null)
            return new ArrayList<>();
        return list;
    }

    //根据ReleaseId获取List
    public List<SubTaskCard> getSubTaskCardByReleaseId(Integer releaseId) {
        List<SubTaskCard> list = subTaskCardMapper.getSubTaskCardListByReleaseId(releaseId);
        if (list == null)
            return new ArrayList<>();
        return list;
    }

    //根据SubTaskCardId删除SubTaskCard
    //在删除之后需要进行重排序
    @Override
    public void deleteSubTaskCardBySubTaskCardID(Integer subTaskCardId) {
        //数据库中删除数据
        SubTaskCard item = subTaskCardMapper.getSubTaskCardById(subTaskCardId);
        Integer taskCardId = item.getTaskId();
        Integer releaseId = item.getReleaseId();
        subTaskCardMapper.deleteSubTaskCard(subTaskCardId);
        List<SubTaskCard> subTaskCardList = getSubTaskCardByTaskIdAndReleaseId(taskCardId, releaseId);
        List<SubTaskCard> needToUpdate = ReorderUtil.reOrderAfterDelete(subTaskCardList);
        for (SubTaskCard subTaskCard : needToUpdate) {
            subTaskCardMapper.updateOrder(subTaskCard.getOrder(), subTaskCard.getId());
        }
    }

    //更新SubTaskCard
    public void updateSubTaskCard(SubTaskCard subTaskCard) {
        subTaskCardMapper.updateSubTaskCard(subTaskCard);
    }

    @Override
    public void addSubTaskCard(SubTaskCard subTaskCard) {
        List<SubTaskCard> subTaskCardList = subTaskCardMapper.getSubTaskCardListByTaskIdAndReleaseId(subTaskCard.getTaskId(), subTaskCard.getReleaseId());
        List<SubTaskCard> needToUpdate = ReorderUtil.reOrderBeforeInsert(subTaskCardList, subTaskCard.getOrder());
        for (SubTaskCard item : needToUpdate) {
            subTaskCardMapper.updateOrder(item.getOrder(), item.getId());
        }
        subTaskCardMapper.addSubTaskCard(subTaskCard);
    }

    public String getSubTaskCardOrder(Integer subTaskCardId) {
        String order = subTaskCardMapper.getSubTaskCardOrder(subTaskCardId);
        return order;
    }
}
