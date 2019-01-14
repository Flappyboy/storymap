package cn.edu.nju.software.storymapping.map.service.Impl;

import cn.edu.nju.software.storymapping.map.dao.WorkspaceMapper;
import cn.edu.nju.software.storymapping.map.entity.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.service.WorkspaceService;

import java.util.List;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    private WorkspaceMapper workspaceMapper;

    //根据id来删除workspace
    public void deleteWorkSpaceById(Integer id) {
        workspaceMapper.delete(id);
    }

    //根据用户id来获取workspace的list
    public List<Workspace> getWorkSpaceById(Integer userId) {
        List<Workspace> workspaceList = workspaceMapper.listByUserId(userId);
        if (workspaceList == null)
            return workspaceList;
        return workspaceList;
    }

    //升级workspace
    public void updateWorkSapce(Workspace workspace) {
        workspaceMapper.update(workspace);
    }

    //创建workspace
    public void CreateWorkSapce(Workspace workspace) {
        workspaceMapper.insert(workspace);
    }

}
