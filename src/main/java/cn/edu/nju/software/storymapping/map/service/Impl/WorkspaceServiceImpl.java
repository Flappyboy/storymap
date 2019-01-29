package cn.edu.nju.software.storymapping.map.service.Impl;

import cn.edu.nju.software.storymapping.map.dao.WorkspaceMapper;
import cn.edu.nju.software.storymapping.map.entity.ActivityCard;
import cn.edu.nju.software.storymapping.map.entity.Release;
import cn.edu.nju.software.storymapping.map.entity.StoryMap;
import cn.edu.nju.software.storymapping.map.entity.Workspace;
import cn.edu.nju.software.storymapping.map.service.ActivityCardService;
import cn.edu.nju.software.storymapping.map.service.ReleaseService;
import cn.edu.nju.software.storymapping.map.service.StoryMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.service.WorkspaceService;

import java.util.Date;
import java.util.List;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    private WorkspaceMapper workspaceMapper;

    @Autowired
    private StoryMapService storyMapService;

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
    public void createWorkSpace(Workspace workspace) {
        workspaceMapper.insert(workspace);

        //在workspace里面需要创建一个storyMap
        StoryMap storyMap = new StoryMap();
        storyMap.setWorkSpaceId(workspace.getId());
        storyMap.setUserId(workspace.getUserId());
        storyMap.setName("storymap");
        storyMap.setDescription("Default Storymap");
        storyMapService.createStoryMap(storyMap);

    }

    @Override
    public void createWorkSpace(Integer userId) {
        Workspace workspace = new Workspace();
        workspace.setDescription("default");
        workspace.setCreateTime(new Date());
        workspace.setName("default workspace");
        workspace.setUserId(userId);
        //创建workspace
        this.createWorkSpace(workspace);
    }

    public int getWorkspaceCount(Integer userId, Integer workspaceId) {
        return workspaceMapper.getWorkspaceCount(userId, workspaceId);
    }

}
