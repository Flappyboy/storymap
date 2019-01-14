package cn.edu.nju.software.storymapping.map.controller.mockdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
public class TaskDto extends ItemDto implements Cloneable {
    List<SubtaskDto> subtasks;
    Integer activityId;

    public TaskDto(Long id, String title, List<SubtaskDto> subtasks, Integer activityId) {
        super(id, title);
        this.subtasks = subtasks;
    }

    public TaskDto clone() {
        List<SubtaskDto> list = new ArrayList<>();
        for (SubtaskDto subtask : subtasks) {
            list.add(subtask.clone());
        }
        return new TaskDto(new Long(getId()), getTitle(), list, activityId);
    }
}
