package cn.edu.nju.software.storymapping.map.service;

import cn.edu.nju.software.storymapping.map.entity.Workspace;

import java.util.List;

public interface WorkspaceService {
    void deleteWorkSpaceById(Integer id);

    List<Workspace> getWorkSpaceById(Integer userId);

    void updateWorkSapce(Workspace workspace);

    void createWorkSpace(Workspace workspace);

    void createWorkSpace(Integer userId);

    int getWorkspaceCount(Integer userId, Integer workspaceId);

}
