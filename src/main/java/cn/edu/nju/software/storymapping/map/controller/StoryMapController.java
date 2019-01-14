package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.*;
import cn.edu.nju.software.storymapping.map.entity.*;
import cn.edu.nju.software.storymapping.map.service.StoryMapService;
import cn.edu.nju.software.storymapping.system.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.ResolutionSyntax;
import java.util.ArrayList;
import java.util.List;

@RestController
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
        StoryMap storyMap = storyMapService.getStoryMapById(id);
        //数据库中的格式
        List<ActivityCard> activityCardList = storyMap.getActivityCardList();
        List<Release> releaseList = storyMap.getReleaseList();
        List<Role> roleList = storyMap.getRoleList();

        //将他们转换成对应的DTO
        List<ActivityDto> activityDtoList = new ArrayList<>();
        List<ReleaseDto> releaseDtoList = new ArrayList<>();
        for (ActivityCard activityCard : activityCardList) {
            List<TaskCard> taskCardList = activityCard.getTaskCardList();
            List<TaskDto> taskDtoList = new ArrayList<>();
            for (TaskCard taskCard : taskCardList) {
                List<SubtaskDto> subtaskDtoList = new ArrayList<>();
                List<SubTaskCard> subTaskCardList = taskCard.getSubTaskCardList();
                for (SubTaskCard subTaskCard : subTaskCardList) {
                    SubtaskDto subtaskDto = new SubtaskDto(new Long(subTaskCard.getId()), subTaskCard.getName(), subTaskCard.getReleaseId().longValue(), subTaskCard.getTaskId().longValue());
                    subtaskDtoList.add(subtaskDto);
                }
                TaskDto taskDto = new TaskDto(new Long(taskCard.getId()), taskCard.getName(), subtaskDtoList, taskCard.getActivityId());
                taskDtoList.add(taskDto);
            }
            ActivityDto activityDto = new ActivityDto(new Long(activityCard.getId()), activityCard.getName(), taskDtoList, activityCard.getStoryMapId());
            activityDtoList.add(activityDto);
        }
        for (Release release : releaseList) {
            releaseDtoList.add(new ReleaseDto(new Long(release.getId()), release.getName(), activityDtoList, release.getStoryMapId()));
        }
        StoryMapDto storyMapDto = new StoryMapDto(new Long(storyMap.getId()), storyMap.getName(), activityDtoList, releaseDtoList, storyMap.getWorkSpaceId());

        return Response.createDefaultResponse().success(storyMapDto);
    }

}

