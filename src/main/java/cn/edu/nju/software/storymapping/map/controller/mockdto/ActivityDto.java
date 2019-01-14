package cn.edu.nju.software.storymapping.map.controller.mockdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class ActivityDto extends ItemDto implements Cloneable{
    List<TaskDto> tasks;

    public ActivityDto(Long id, String title, List<TaskDto> tasks) {
        super(id, title);
        this.tasks = tasks;
    }

    public ActivityDto clone(){
        List<TaskDto> list = new ArrayList<>();
        for (TaskDto task: tasks) {
            list.add(task.clone());
        }
        return new ActivityDto(new Long(getId()), getTitle(), list);
    }
}
