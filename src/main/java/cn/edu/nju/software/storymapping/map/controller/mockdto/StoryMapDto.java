package cn.edu.nju.software.storymapping.map.controller.mockdto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class StoryMapDto extends ItemDto{
    List<ActivityDto> activities;
    List<ReleaseDto> releases;

    public StoryMapDto(Long id, String title, List<ActivityDto> activities, List<ReleaseDto> releases) {
        super(id, title);
        this.activities = activities;
        this.releases = releases;
    }
}
