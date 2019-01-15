package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.SubtaskDto;
import cn.edu.nju.software.storymapping.map.entity.SubTaskCard;
import cn.edu.nju.software.storymapping.map.service.SubTaskCardService;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class SubTaskCardController {
    @Autowired
    SubTaskCardService subTaskCardService;

    @PostMapping(value = "/subtask")
    public Response addSubtask(SubtaskDto dto) {
        SubTaskCard subTaskCard = wrapSubTaskDto(dto);
        subTaskCardService.addSubTaskCard(subTaskCard);
        dto.setId(subTaskCard.getId().longValue());
        return Response.createDefaultResponse().success(dto);

    }

    @PutMapping("/subtask")
    public Response updateSubTask(SubtaskDto dto) {
        SubTaskCard subTaskCard = wrapSubTaskDto(dto);
        subTaskCardService.updateSubTaskCard(subTaskCard);
        return Response.createDefaultResponse().success(subTaskCard);
    }

    public SubTaskCard wrapSubTaskDto(SubtaskDto dto) {

        SubTaskCard subTaskCard = new SubTaskCard();
        if (dto.getId() != null)
            subTaskCard.setId(dto.getId().intValue());
        subTaskCard.setCreatorId(UserUtil.currentUser().getId());
        subTaskCard.setTaskId(dto.getTaskId().intValue());
        subTaskCard.setName(dto.getTitle());
        subTaskCard.setOrder(dto.getOrder() + "");
        subTaskCard.setReleaseId(dto.getReleaseId().intValue());
        return subTaskCard;
    }
}
