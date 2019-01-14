package cn.edu.nju.software.storymapping.map.controller.mockdto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
public class ReleaseDto extends ItemDto {
    List<ActivityDto> activities;
    Integer storyMapId;

    public ReleaseDto(Long id, String title, List<ActivityDto> activities, Integer storyMapId) {
        super(id, title);
        this.activities = extractActivities(activities);
    }

    public List<ActivityDto> extractActivities(List<ActivityDto> activities) {
        List<ActivityDto> activityDtoList = new ArrayList<>();
        for (ActivityDto activity : activities) {
            activityDtoList.add(activity.clone());
        }
        for (ActivityDto activity : activityDtoList) {
            for (TaskDto task : activity.getTasks()) {
                List<SubtaskDto> list = new ArrayList<>();
                for (SubtaskDto subtask : task.getSubtasks()) {
                    if (!subtask.getReleaseId().equals(getId()))
                        list.add(subtask);
                }
                task.getSubtasks().removeAll(list);
            }
        }
        return activityDtoList;
    }
}
