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

    public WorkspaceDto(Long id, String title, String desc, List<StoryMapDto> storyMaps) {
        super(id, title, desc);
        setDesc(desc);
        this.storyMaps = storyMaps;
    }
}
