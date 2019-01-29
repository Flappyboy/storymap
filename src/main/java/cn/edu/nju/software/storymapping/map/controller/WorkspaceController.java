package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.WorkspaceDto;
import cn.edu.nju.software.storymapping.map.entity.ActivityCard;
import cn.edu.nju.software.storymapping.map.entity.StoryMap;
import cn.edu.nju.software.storymapping.map.entity.Workspace;
import cn.edu.nju.software.storymapping.map.service.ActivityCardService;
import cn.edu.nju.software.storymapping.map.service.StoryMapService;
import cn.edu.nju.software.storymapping.map.service.WorkspaceService;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class WorkspaceController {

    @Autowired
    WorkspaceService workspaceService;
    @Autowired
    StoryMapService storyMapService;
    @Autowired
    ActivityCardService activityCardService;

    @PutMapping("/workspace")
    public Response updateWorkspace(WorkspaceDto workspaceDto) {
        Workspace workspace = new Workspace();
        workspace.setId(workspaceDto.getId().intValue());
        workspace.setName(workspaceDto.getTitle());
        workspaceService.updateWorkSapce(workspace);
        return Response.createDefaultResponse().success(workspaceDto);

    }

    @DeleteMapping("/workspace/{id}")
    public Response deleteWorkspace(@PathVariable Integer id) {
        workspaceService.deleteWorkSpaceById(id);
        return Response.createDefaultResponse().success("删除成功");
    }

    @PostMapping("/workspace")
    public Response createWorkspace(WorkspaceDto workspaceDto) {
        Workspace workspace = new Workspace();
        workspace.setUserId(UserUtil.currentUserId());
        workspace.setName(workspaceDto.getTitle());
        workspace.setCreateTime(new Date());
        workspace.setDescription(workspaceDto.getDesc());
        workspaceService.createWorkSpace(workspace);
        workspaceDto.setId(workspace.getId().longValue());
        return Response.createDefaultResponse().success(workspaceDto);
    }

}
