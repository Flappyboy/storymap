package cn.edu.nju.software.storymapping.map.controller.mockdto;

import cn.edu.nju.software.storymapping.map.entity.StoryMap;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
public class WorkspaceDto extends ItemDto {
    List<StoryMapDto> storyMaps;

    public WorkspaceDto(Long id, String title, List<StoryMapDto> storyMaps) {
        super(id, title);
        this.storyMaps = storyMaps;
    }
}
