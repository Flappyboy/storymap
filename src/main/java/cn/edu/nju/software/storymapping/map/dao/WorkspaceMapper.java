package cn.edu.nju.software.storymapping.map.dao;

import java.util.List;

import cn.edu.nju.software.storymapping.map.entity.Workspace;
import org.apache.ibatis.annotations.Param;

public interface WorkspaceMapper {
    public void insert(Workspace workspace);

    public void delete(Integer id);

    public void update(Workspace workspace);

    public Workspace getById(Integer id);

    public List<Workspace> listAll();

    public List<Workspace> listByUserId(Integer id);

    public Integer getWorkspaceCount(@Param("userId") Integer userId, @Param("workspaceId") Integer workspaceId);
}
