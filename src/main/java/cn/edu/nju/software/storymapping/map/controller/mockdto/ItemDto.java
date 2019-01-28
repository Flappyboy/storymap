package cn.edu.nju.software.storymapping.map.controller.mockdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ItemDto {
    Long id;
    String title;
    String desc;
    Long order;

    public ItemDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public ItemDto(Long id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }
}
