package cn.edu.nju.software.storymapping.map.dao;

import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubTaskCardMapper {
    public void updateSubTaskCard(SubTaskCard subTaskCard);

    public void deleteSubTaskCard(Integer id);

    public void addSubTaskCard(SubTaskCard subTaskCard);

    public List<SubTaskCard> getSubTaskCardListByTaskId(Integer taskCardId);

    public List<SubTaskCard> getSubTaskCardListByReleaseId(Integer releaseId);

    public void updateOrder(@Param("order") String order, @Param("subTaskCardId") Integer subTaskCardId);

    public Integer getTaskCardId(Integer subTaskCardId);
}
