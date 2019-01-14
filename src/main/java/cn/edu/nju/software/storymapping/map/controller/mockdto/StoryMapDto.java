package cn.edu.nju.software.storymapping.map.controller.mockdto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class StoryMapDto extends ItemDto {
    List<ActivityDto> activities;
    List<ReleaseDto> releases;
    Integer workSpaceId;

    public StoryMapDto(Long id, String title, List<ActivityDto> activities, List<ReleaseDto> releases, Integer workSpaceId) {
        super(id, title);
        this.activities = activities;
        this.releases = releases;
    }
}
