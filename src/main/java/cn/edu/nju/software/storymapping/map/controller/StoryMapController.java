package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.*;
import cn.edu.nju.software.storymapping.map.entity.*;
import cn.edu.nju.software.storymapping.map.service.StoryMapService;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class StoryMapController {
    @Autowired
    private StoryMapService storyMapService;

    @PutMapping("/storymap")
    public Response updateStroyMap(StoryMapDto dto) {
        StoryMap storyMap = wrapStoryMap(dto);
        storyMapService.updateStoryMap(storyMap);
        return Response.createDefaultResponse().success(dto);
    }

    public StoryMap wrapStoryMap(StoryMapDto dto) {
        StoryMap storyMap = new StoryMap();
        if (dto.getId() != null)
            storyMap.setId(dto.getId().intValue());
        storyMap.setName(dto.getTitle());
        storyMap.setWorkSpaceId(dto.getWorkSpaceId());
        return storyMap;
    }

    @GetMapping("/storymap/{id}")
    public Response getStoryMapById(@PathVariable("id") int id) {
        //验证是否有权限查看该storymap

        if (storyMapService.getAuthorityForStorymap(UserUtil.currentUser().getId(), id) != null) {
            return Response.createDefaultResponse().fail("没有权限访问！");
        }
        StoryMap storyMap = storyMapService.getStoryMapById(id);
        StoryMapDto storyMapDto = transferToStoryMapDto(storyMap);
        return Response.createDefaultResponse().success(storyMapDto);
    }

    public StoryMapDto transferToStoryMapDto(StoryMap storyMap) {
        // 数据库中的格式
        List<ActivityCard> activityCardList = storyMap.getActivityCardList();
        List<Release> releaseList = storyMap.getReleaseList();
        List<Role> roleList = storyMap.getRoleList();

        // 将他们转换成对应的DTO
        List<RoleDto> roleDtoList = new ArrayList<>();
        List<ActivityDto> activityDtoList = new ArrayList<>();
        List<ReleaseDto> releaseDtoList = new ArrayList<>();

        for (ActivityCard activityCard : activityCardList) {
            List<Role> roleActivityList = activityCard.getRoleList();
            List<RoleDto> roleActvityDtoList = new ArrayList<RoleDto>();
            for (Role role : roleActivityList) {
                RoleDto roleDto = new RoleDto(role.getId().longValue(), role.getName(), role.getStoryMapId(), role.getImageId());
                roleActvityDtoList.add(roleDto);
            }

            List<TaskCard> taskCardList = activityCard.getTaskCardList();
            List<TaskDto> taskDtoList = new ArrayList<>();
            for (TaskCard taskCard : taskCardList) {
                List<SubtaskDto> subtaskDtoList = new ArrayList<>();
                List<SubTaskCard> subTaskCardList = taskCard.getSubTaskCardList();
                for (SubTaskCard subTaskCard : subTaskCardList) {
                    SubtaskDto subtaskDto = new SubtaskDto(new Long(subTaskCard.getId()), subTaskCard.getName(),
                            subTaskCard.getReleaseId().longValue(), subTaskCard.getTaskId().longValue());
                    subtaskDtoList.add(subtaskDto);
                }
                TaskDto taskDto = new TaskDto(new Long(taskCard.getId()), taskCard.getName(), subtaskDtoList,
                        taskCard.getActivityId());
                taskDtoList.add(taskDto);
            }
            ActivityDto activityDto = new ActivityDto(new Long(activityCard.getId()), activityCard.getName(),
                    taskDtoList, roleActvityDtoList, activityCard.getStoryMapId());
            activityDtoList.add(activityDto);
        }
        for (Release release : releaseList) {
            releaseDtoList.add(new ReleaseDto(new Long(release.getId()), release.getName(), activityDtoList,
                    release.getStoryMapId()));
        }
        for (Role role : roleList) {
            roleDtoList
                    .add(new RoleDto(new Long(role.getId()), role.getName(), role.getStoryMapId(), role.getImageId()));
        }
        StoryMapDto storyMapDto = new StoryMapDto(new Long(storyMap.getId()), storyMap.getName(), activityDtoList,
                releaseDtoList, roleDtoList, storyMap.getWorkSpaceId());
        return storyMapDto;
    }

    @GetMapping("/storymap/workspace/{workspaceId}")
    public Response getStoryMapByWorkSpaceId(@PathVariable("workspaceId") int workspaceId) {
        //验证是否有查看workspace的权限
        List<StoryMap> storyMapList = storyMapService.getStoryMapByWorkSpaceId(workspaceId);
        List<StoryMapDto> storyMapDtoList = new ArrayList<>();
        for (StoryMap storyMap : storyMapList) {
            if (storyMapService.getAuthorityForStorymap(UserUtil.currentUser().getId(), storyMap.getId()) != null)
                storyMapDtoList.add(transferToStoryMapDto(storyMap));
        }
        if (storyMapDtoList.size() == 0)
            return Response.createDefaultResponse().fail("该工作空间中的storymap没有访问权限");

        WorkspaceDto workspaceDto = new WorkspaceDto(new Integer(workspaceId).longValue(), null, storyMapDtoList);
        return Response.createDefaultResponse().success(workspaceDto);
    }

}
