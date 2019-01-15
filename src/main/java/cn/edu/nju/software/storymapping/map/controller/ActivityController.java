package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.ActivityDto;
import cn.edu.nju.software.storymapping.map.dao.ActivityCardMapper;
import cn.edu.nju.software.storymapping.map.entity.ActivityCard;
import cn.edu.nju.software.storymapping.map.service.ActivityCardService;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.ResolutionSyntax;
import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class ActivityController {

    @Autowired
    ActivityCardService activityCardService;

    @PostMapping(value = "/activity")
    public Response addActivity(ActivityDto dto) {
        ActivityCard activityCard = wrapActivityDto(dto);
        activityCardService.addActivity(activityCard);
        dto.setId(new Long(activityCard.getId()));
        return Response.createDefaultResponse().success(dto);
    }

    @PutMapping(value = "/activity")
    public Response updateActivity(ActivityDto dto) {
        ActivityCard activityCard = wrapActivityDto(dto);
        activityCardService.updateActivity(activityCard);
        return Response.createDefaultResponse().success(dto);

    }

    @DeleteMapping("/activity/{id}")
    public Response deleteActivity(@PathVariable Integer id) {
        activityCardService.deleteActivity(id);
        return Response.createDefaultResponse().success(null);
    }

    public ActivityCard wrapActivityDto(ActivityDto dto) {
        ActivityCard activityCard = new ActivityCard();
        if (dto.getId() != null)
            activityCard.setId(dto.getId().intValue());
        else activityCard.setCreateTime(new Date());
        activityCard.setOrder(dto.getOrder() + "");
        activityCard.setName(dto.getTitle());
        activityCard.setCreateTime(new Date());
        activityCard.setCreatorId(UserUtil.currentUser().getId());
        activityCard.setStoryMapId(dto.getStoryMapId());
        return activityCard;
    }
}
