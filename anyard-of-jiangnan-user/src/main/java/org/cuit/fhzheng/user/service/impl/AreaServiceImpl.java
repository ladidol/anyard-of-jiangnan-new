package org.cuit.fhzheng.user.service.impl;

import org.cuit.fhzheng.api.user.vo.AreaVO;
import org.cuit.fhzheng.common.cache.constant.CacheNames;
import org.cuit.fhzheng.common.exception.anyardOfJiangnanException;
import org.cuit.fhzheng.user.mapper.AreaMapper;
import org.cuit.fhzheng.user.model.Area;
import org.cuit.fhzheng.user.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 省市区地区信息
 *
 * @author YXF
 * @date 2020-11-25 14:48:52
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<AreaVO> list() {
        return areaMapper.list();
    }

    @Override
    @Cacheable(cacheNames = CacheNames.AREA_INFO_KEY, key = "'areaList'", sync = true)
    public List<AreaVO> getAreaListInfo() {
        List<AreaVO> areaList = areaMapper.getAreaListInfo();
        for (AreaVO province:areaList){
            List<Long> cityAll = new ArrayList<>();
            for (AreaVO city:province.getAreas()){
                cityAll.add(city.getAreaId());
                List<Long> areaAll = new ArrayList<>();
                for (AreaVO area:city.getAreas()){
                    areaAll.add(area.getAreaId());
                }
                city.setAreaIds(areaAll);
            }
            province.setAreaIds(cityAll);
        }
        return areaList;
    }

    @Override
    public List<AreaVO> listAreaOfEnable() {
        List<AreaVO> list = areaMapper.listAreaOfEnable();
        return list;
    }

    @Override
    public AreaVO getByAreaId(Long areaId) {
        return areaMapper.getByAreaId(areaId);
    }

    @Override
    public void save(Area area) {
        areaMapper.save(area);
    }

    @Override
    public void update(Area area) {
        areaMapper.update(area);
    }

    @Override
    public void deleteById(Long areaId) {
        int areaNum = areaMapper.countByAreaId(areaId);
        if (areaNum > 0) {
            throw new anyardOfJiangnanException("请先删除子地区");
        }
        areaMapper.deleteById(areaId);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.AREA_KEY, key = "'list:' + #pid")
    public List<AreaVO> listByPid(Long pid) {
        return areaMapper.listByPid(pid);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = CacheNames.AREA_INFO_KEY, key = "'areaList'"),
            @CacheEvict(cacheNames = CacheNames.AREA_KEY, key = "'list:' + #pid")
    })
    public void removeAllCache(Long pid) {

    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = CacheNames.AREA_KEY, key = "'list:' + #pid"),
            @CacheEvict(cacheNames = CacheNames.AREA_INFO_KEY, key = "'areaList'")
    })
    public void removeAreaCacheByParentId(Long pid) {

    }
}
