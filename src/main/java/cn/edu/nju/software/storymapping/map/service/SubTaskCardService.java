package cn.edu.nju.software.storymapping.map.service;

import cn.edu.nju.software.storymapping.map.controller.mockdto.SubtaskDto;
import cn.edu.nju.software.storymapping.map.entity.ActivityCard;
import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;

import java.util.List;

public interface SubTaskCardService {
    public List<SubTaskCard> getSubTaskCardByTaskIdAndReleaseId(Integer taskId, Integer releaseId);

    public List<SubTaskCard> getSubTaskCardByReleaseId(Integer releaseId);

    public void deleteSubTaskCardBySubTaskCardID(Integer subTaskCardId);

    public void updateSubTaskCard(SubTaskCard subTaskCard);

    public void addSubTaskCard(SubTaskCard subTaskCard);

    public String getSubTaskCardOrder(Integer subTaskCardId);

    //平行移动
    public boolean moveSideways(SubTaskCard subTaskCard);

}
