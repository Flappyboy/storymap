package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.TaskDto;
import cn.edu.nju.software.storymapping.map.entity.TaskCard;
import cn.edu.nju.software.storymapping.map.service.TaskCardService;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class TaskCardController {
    @Autowired
    TaskCardService taskCardService;

    @PostMapping(value = "/task")
    public Response addTask(TaskDto dto) {
        TaskCard taskCard = wrapTaskDto(dto);
        taskCardService.createTaskCard(taskCard);
        dto.setId(taskCard.getId().longValue());
        return Response.createDefaultResponse().success(dto);
    }

    @PutMapping("/task")
    public Response updateTask(TaskDto dto) {
        TaskCard taskCard = wrapTaskDto(dto);
        taskCardService.updateTaskCard(taskCard);
        return Response.createDefaultResponse().success(dto);
    }


    public TaskCard wrapTaskDto(TaskDto dto) {

        TaskCard taskCard = new TaskCard();
        if (dto.getId() != null)
            taskCard.setId(dto.getId().intValue());
        taskCard.setCreateTime(new Date());
        taskCard.setCreatorId(UserUtil.currentUser().getId());
        taskCard.setActivityId(dto.getActivityId());
        taskCard.setOrder(dto.getOrder() + "");
        return taskCard;
    }

}
