package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.SubtaskDto;
import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;
import cn.edu.nju.software.storymapping.map.service.SubTaskCardService;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class SubTaskCardController {
    @Autowired
    SubTaskCardService subTaskCardService;

    @PostMapping(value = "/subtask")
    public Response addSubtask(SubtaskDto dto) {
        if (dto == null)
            return Response.createDefaultResponse().fail("dto为null");
        if (dto.getTaskId() == null || dto.getOrder() == null)
            return Response.createDefaultResponse().fail("taskId或order为null");
        SubTaskCard subTaskCard = wrapSubTaskDto(dto);
        subTaskCardService.addSubTaskCard(subTaskCard);
        dto.setId(subTaskCard.getId().longValue());
        return Response.createDefaultResponse().success(dto);

    }

    @PutMapping("/subtask")
    public Response updateSubTask(SubtaskDto dto) {
        if (dto == null)
            return Response.createDefaultResponse().fail("dto为null");
        if (dto.getId() == null)
            return Response.createDefaultResponse().fail("dto中Id为null");
        String order = subTaskCardService.getSubTaskCardOrder(dto.getId().intValue());
        dto.setOrder((long) Integer.parseInt(order));
        SubTaskCard subTaskCard = wrapSubTaskDto(dto);
        subTaskCardService.updateSubTaskCard(subTaskCard);
        System.out.println("加入完毕");
        return Response.createDefaultResponse().success(dto);
    }

    @DeleteMapping("/subtask/{id}")
    public Response deleteSubTask(@PathVariable Integer id) {
        subTaskCardService.deleteSubTaskCardBySubTaskCardID(id);
        return Response.createDefaultResponse().success(null);
    }

    public SubTaskCard wrapSubTaskDto(SubtaskDto dto) {
        SubTaskCard subTaskCard = new SubTaskCard();
        if (dto.getId() != null)
            subTaskCard.setId(dto.getId().intValue());
        else
            subTaskCard.setCreateTime(new Date());
        subTaskCard.setCreatorId(UserUtil.currentUser().getId());
        if (dto.getTaskId() != null)
            subTaskCard.setTaskId(dto.getTaskId().intValue());
        subTaskCard.setName(dto.getTitle());
        subTaskCard.setOrder(dto.getOrder() + "");
        if (dto.getReleaseId() != null)
            subTaskCard.setReleaseId(dto.getReleaseId().intValue());
        return subTaskCard;
    }
}
