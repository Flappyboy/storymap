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
	List<RoleDto> roles;
	Integer storyMapId;

	public ActivityDto(Long id, String title, List<TaskDto> tasks, List<RoleDto> roles, Integer storyMapId) {
		super(id, title);
		this.tasks = tasks;
		this.roles = roles;
		this.storyMapId = storyMapId;
	}

	public ActivityDto clone() {
		List<TaskDto> list = new ArrayList<>();
		for (TaskDto task : tasks) {
			list.add(task.clone());
		}
		List<RoleDto> list2=new ArrayList<>();
		for(RoleDto role:roles){
			list2.add(role.clone());
		}
		return new ActivityDto(getId(), getTitle(), list, list2,storyMapId);
	}
}
