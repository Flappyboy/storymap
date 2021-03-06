package cn.edu.nju.software.storymapping.map.service;

import java.util.List;

import cn.edu.nju.software.storymapping.map.entity.Release;
import org.apache.ibatis.annotations.Param;

public interface ReleaseService {
    void addRelease(Release release);

    void deleteRelease(Integer id);

    void updateRelease(Release release);

    Release getReleaseById(Integer id);

    List<Release> listAll();

    List<Release> listReleaseByStoryMapId(int id);

    String getReleaseOrder(Integer releaseId);


}
