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
		if (dto == null)
			return Response.createDefaultResponse().fail("dto为null");
		if (dto.getStoryMapId() == null || dto.getOrder() == null)
			return Response.createDefaultResponse().fail("storyMapId或order为null");
		ActivityCard activityCard = wrapActivityDto(dto);
		activityCardService.addActivity(activityCard);
		dto.setId(new Long(activityCard.getId()));
		return Response.createDefaultResponse().success(dto);
	}

	@PutMapping(value = "/activity")
	public Response updateActivity(ActivityDto dto) {
		if (dto == null) {
			return Response.createDefaultResponse().fail("dto为null");
		}
		if (dto.getId() == null)
			return Response.createDefaultResponse().fail("dto的id为null");
		String activityOrder = activityCardService.getActivityOrder(dto.getId().intValue());
		dto.setOrder((long) Integer.parseInt(activityOrder));
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
		if (dto.getId() != null)// 更新
			activityCard.setId(dto.getId().intValue());
		else
			activityCard.setCreateTime(new Date());// 插入

		activityCard.setOrder(dto.getOrder() + "");
		activityCard.setName(dto.getTitle());
		activityCard.setCreatorId(UserUtil.currentUser().getId());
		activityCard.setStoryMapId(dto.getStoryMapId());
		return activityCard;
	}
}
