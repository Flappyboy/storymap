package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.ReleaseDto;
import cn.edu.nju.software.storymapping.map.entity.Release;
import cn.edu.nju.software.storymapping.map.service.ReleaseService;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class ReleaseController {
    @Autowired
    ReleaseService releaseService;

    @PostMapping(value = "/release")
    public Response addRelease(ReleaseDto dto) {
        if (dto == null)
            return Response.createDefaultResponse().fail("dto为null");
        if (dto.getStoryMapId() == null || dto.getOrder() == null)
            return Response.createDefaultResponse().fail("storyMapId或order为null");
        Release release = wrapRelease(dto);
        releaseService.addRelease(release);
        dto.setId(release.getId().longValue());
        return Response.createDefaultResponse().success(dto);
    }

    @PutMapping("/release")
    public Response updateRelease(ReleaseDto dto) {
        if (dto == null)
            return Response.createDefaultResponse().fail("dto为null");
        if (dto.getId() == null)
            return Response.createDefaultResponse().fail("Id为null");
        String order = releaseService.getReleaseOrder(dto.getId().intValue());
        dto.setOrder((long) Integer.parseInt(order));
        Release release = wrapRelease(dto);
        releaseService.updateRelease(release);
        return Response.createDefaultResponse().success(dto);
    }

    @DeleteMapping("/release/{id}")
    public Response deleteRelease(@PathVariable Integer id) {
        releaseService.deleteRelease(id);
        return Response.createDefaultResponse().success(null);
    }


    public Release wrapRelease(ReleaseDto dto) {
        Release release = new Release();
        if (dto.getId() != null)
            release.setId(dto.getId().intValue());
        else release.setCreateTime(new Date());
        release.setCreateTime(new Date());
        release.setCreatorId(UserUtil.currentUser().getId());
        release.setStoryMapId(dto.getStoryMapId());
        release.setOrder(dto.getOrder() + "");
        release.setName(dto.getTitle());
        return release;
    }

}
