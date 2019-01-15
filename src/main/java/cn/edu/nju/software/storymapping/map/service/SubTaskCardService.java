package cn.edu.nju.software.storymapping.map.service;

import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;

import java.util.List;

public interface SubTaskCardService {
    public List<SubTaskCard> getSubTaskCardByTaskId(Integer taskId);

    public List<SubTaskCard> getSubTaskCardByReleaseId(Integer releaseId);

    public void deleteSubTaskCardBySubTaskCardID(Integer subTaskCardId);

    public void updateSubTaskCard(SubTaskCard subTaskCard);

    public void addSubTaskCard(SubTaskCard subTaskCard);

}
