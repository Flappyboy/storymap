package cn.edu.nju.software.storymapping.map.controller.mockdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
public class ActivityDto extends ItemDto implements Cloneable {
    List<TaskDto> tasks;
    Integer storyMapId;

    public ActivityDto(Long id, String title, List<TaskDto> tasks, Integer storyMapId) {
        super(id, title);
        this.tasks = tasks;
        this.storyMapId = storyMapId;
    }

    public ActivityDto clone() {
        List<TaskDto> list = new ArrayList<>();
        for (TaskDto task : tasks) {
            list.add(task.clone());
        }
        return new ActivityDto(getId(), getTitle(), list, storyMapId);
    }
}
