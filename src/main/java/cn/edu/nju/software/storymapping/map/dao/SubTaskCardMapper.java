package cn.edu.nju.software.storymapping.map.dao;

import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SubTaskCardMapper {
    public void updateSubTaskCard(SubTaskCard subTaskCard);

    public void deleteSubTaskCard(Integer id);

    public void addSubTaskCard(SubTaskCard subTaskCard);

    public List<SubTaskCard> getSubTaskCardListByTaskIdAndReleaseId(@Param("taskCardId") Integer taskCardId, @Param("releaseId") Integer releaseId);

    public List<SubTaskCard> getSubTaskCardListByReleaseId(Integer releaseId);

    public void updateOrder(@Param("order") String order, @Param("subTaskCardId") Integer subTaskCardId);

    public Integer getTaskCardId(Integer subTaskCardId);

    public String getSubTaskCardOrder(Integer subTaskCardId);

    public SubTaskCard getSubTaskCardById(Integer subtaskCardId);

    public List<SubTaskCard> getSubTaskCardListByTaskId(Integer taskCardId);
}
