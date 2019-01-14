package cn.edu.nju.software.storymapping.map.controller.mockdto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class SubtaskDto extends ItemDto implements Cloneable{
    Long releaseId;

    public SubtaskDto(Long id, String title, Long releaseId) {
        super(id, title);
        this.releaseId = releaseId;
    }

    public SubtaskDto clone(){
        return new SubtaskDto(new Long(getId()), getTitle(), new Long(getReleaseId()));
    }
}
