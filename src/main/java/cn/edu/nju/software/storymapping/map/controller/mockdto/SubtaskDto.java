package cn.edu.nju.software.storymapping.map.controller.mockdto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class SubtaskDto extends ItemDto implements Cloneable {
    Long releaseId;
    Long taskId;


    public SubtaskDto(Long id, String title, Long releaseId, Long taskId) {
        super(id, title);
        this.releaseId = releaseId;
        this.taskId = taskId;
    }

    public SubtaskDto clone() {
        return new SubtaskDto(getId(), getTitle(), new Long(getReleaseId()), taskId);
    }
}
