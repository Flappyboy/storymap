package cn.edu.nju.software.storymapping.map.service.Impl;

import cn.edu.nju.software.storymapping.map.dao.SubTaskCardMapper;
import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;
import cn.edu.nju.software.storymapping.map.utils.ReorderUtil;
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
    public List<SubTaskCard> getSubTaskCardByTaskId(Integer taskId) {
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
    public void deleteSubTaskCardBySubTaskCardID(Integer subTaskCardId, Integer taskCardId) {
        //数据库中删除数据
        subTaskCardMapper.deleteSubTaskCard(subTaskCardId);
        List<SubTaskCard> subTaskCardList = getSubTaskCardByTaskId(taskCardId);
        ReorderUtil.reOrder(subTaskCardList);
        for (SubTaskCard subTaskCard : subTaskCardList) {
            subTaskCardMapper.updateOrder(subTaskCard.getOrder(), subTaskCard.getId());
        }
    }

    //更新SubTaskCard
    public void updateSubTaskCard(SubTaskCard subTaskCard) {
        subTaskCardMapper.updateSubTaskCard(subTaskCard);
    }

    @Override
    public void addSubTaskCard(SubTaskCard subTaskCard) {
        subTaskCardMapper.addSubTaskCard(subTaskCard);
    }
}
