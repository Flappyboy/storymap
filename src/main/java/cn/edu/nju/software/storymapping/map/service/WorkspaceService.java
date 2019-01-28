package cn.edu.nju.software.storymapping.map.service;

import cn.edu.nju.software.storymapping.map.entity.Workspace;

import java.util.List;

public interface WorkspaceService {
    public void deleteWorkSpaceById(Integer id);

    public List<Workspace> getWorkSpaceById(Integer userId);

    public void updateWorkSapce(Workspace workspace);

    public void CreateWorkSapce(Workspace workspace);

    public int getWorkspaceCount(Integer userId, Integer workspaceId);

}
