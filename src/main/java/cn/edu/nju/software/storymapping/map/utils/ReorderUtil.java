package cn.edu.nju.software.storymapping.map.utils;

import cn.edu.nju.software.storymapping.map.entity.Orderable;
import io.swagger.models.auth.In;

import java.util.List;

public class ReorderUtil {

    public static <T extends Orderable> void reOrder(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setOrder("" + i);
        }

    }
}
