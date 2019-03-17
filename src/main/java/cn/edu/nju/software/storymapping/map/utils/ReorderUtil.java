package cn.edu.nju.software.storymapping.map.utils;

import cn.edu.nju.software.storymapping.map.entity.Orderable;
import io.swagger.models.auth.In;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

public class ReorderUtil {
    //删除数据后进行重新排序
    public static <T extends Orderable> List<T> reOrderAfterDelete(List<T> list) {
        List<T> needToUpdate = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i != Integer.parseInt(list.get(i).getOrder())) {
                //将order更新为最新值
                list.get(i).setOrder(i + "");
                //将对象添加到要更新的list中
                needToUpdate.add(list.get(i));
            }
        }
        return needToUpdate;
    }


    //插入数据前进行排序
    public static <T extends Orderable> List<T> reOrderBeforeInsert(List<T> list, String order) {
        List<T> needToUpdate = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //获取当前的order
            Integer originOrder = Integer.parseInt(list.get(i).getOrder());
            //如果当前的order比需要插入的order大或者相等，说明当前的位置需要空出来
            if (originOrder >= Integer.parseInt(order)) {
                list.get(i).setOrder((originOrder + 1) + "");
                needToUpdate.add(list.get(i));
            }
        }
        return needToUpdate;
    }

    public static <T extends Orderable> List<T> reOrderBeforeInsert(List<T> list, String order, Integer id) {
        List<T> needToUpdate = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //获取当前的order
            Integer originOrder = Integer.parseInt(list.get(i).getOrder());
            //如果当前的order比需要插入的order大或者相等，说明当前的位置需要空出来
            if (originOrder >= Integer.parseInt(order) && !list.get(i).getId().equals(id)) {
                list.get(i).setOrder((originOrder + 1) + "");
                needToUpdate.add(list.get(i));
            }
        }
        return needToUpdate;
    }
}
