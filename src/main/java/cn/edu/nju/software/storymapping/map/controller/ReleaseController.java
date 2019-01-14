package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.ReleaseDto;
import cn.edu.nju.software.storymapping.map.entity.Release;
import cn.edu.nju.software.storymapping.map.service.ReleaseService;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ReleaseController {
    @Autowired
    ReleaseService releaseService;

    @PostMapping(value = "/release")
    public Response addRelease(ReleaseDto dto) {
        Release release = wrapRelease(dto);
        releaseService.addRelease(release);
        dto.setId(release.getId().longValue());
        return Response.createDefaultResponse().success(dto);

    }

    @PutMapping("/release")
    public Response updateRelease(ReleaseDto dto) {
        Release release = wrapRelease(dto);
        releaseService.updateRelease(release);
        return Response.createDefaultResponse().success(dto);
    }

    public Release wrapRelease(ReleaseDto dto) {
        Release release = new Release();
        if (dto.getId() != null)
            release.setId(dto.getId().intValue());
        release.setCreateTime(new Date());
        release.setCreatorId(UserUtil.currentUser().getId());
        release.setStoryMapId(dto.getStoryMapId());
        release.setOrder(dto.getOrder() + "");
        release.setName(dto.getTitle());
        return release;
    }

}
