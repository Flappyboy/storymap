package cn.edu.nju.software.storymapping.map.controller.mockdto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class RoleDto extends ItemDto implements Cloneable {
	Integer storyMapId;

	Integer imageId;

	public RoleDto(Long id, String title, Integer storyMapId, Integer imageId) {
		super(id, title);

		this.storyMapId = storyMapId;
		this.imageId = imageId;

	}

	public RoleDto clone() {

		return new RoleDto(getId(), getTitle(), storyMapId, imageId);
	}
}
