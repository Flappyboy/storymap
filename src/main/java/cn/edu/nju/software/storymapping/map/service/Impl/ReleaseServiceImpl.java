package cn.edu.nju.software.storymapping.map.service.Impl;

import java.util.List;

import cn.edu.nju.software.storymapping.map.utils.ReorderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.dao.ReleaseMapper;
import cn.edu.nju.software.storymapping.map.entity.Release;
import cn.edu.nju.software.storymapping.map.service.ReleaseService;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    private static final Logger logger = LoggerFactory.getLogger(ReleaseServiceImpl.class);
    @Autowired
    private ReleaseMapper releaseMapper;

    //添加数据之前进行重排序
    @Override
    public void addRelease(Release release) {
        List<Release> releaseList = releaseMapper.listByStoryMapId(release.getStoryMapId());
        List<Release> needToUpdate = ReorderUtil.reOrderBeforeInsert(releaseList, release.getOrder());
        for (Release item : needToUpdate) {
            releaseMapper.updateOrder(item.getId(), item.getOrder());
        }
        releaseMapper.insert(release);
    }

    @Override
    public void deleteRelease(Integer id) {
        Integer storyMapId = releaseMapper.getStoryMapId(id);
        releaseMapper.delete(id);
        List<Release> releaseList = releaseMapper.listByStoryMapId(storyMapId);
        ReorderUtil.reOrderAfterDelete(releaseList);
        for (Release release : releaseList) {
            releaseMapper.updateOrder(release.getId(), release.getOrder());
        }
    }

    @Override
    public void updateRelease(Release release) {
        // TODO Auto-generated method stub
        logger.debug("ReleaseServiceImpl::updateRelease Release = {}", release.toString());
        releaseMapper.update(release);
    }

    @Override
    public Release getReleaseById(Integer id) {
        // TODO Auto-generated method stub
        logger.debug("ReleaseServiceImpl::getReleaseById id = {}", id);
        Release release = releaseMapper.getById(id);
        return release;
    }

    @Override
    public List<Release> listAll() {
        // TODO Auto-generated method stub
        List<Release> result = releaseMapper.listAll();
        return result;
    }

    @Override
    public List<Release> listReleaseByStoryMapId(int id) {
        // TODO Auto-generated method stub
        logger.debug("ReleaseServiceImpl::listReleaseByStoryMapId id = {}", id);
        List<Release> result = releaseMapper.listByStoryMapId(id);
        return result;
    }

}
