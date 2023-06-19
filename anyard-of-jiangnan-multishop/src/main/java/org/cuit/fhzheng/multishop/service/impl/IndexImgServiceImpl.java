package org.cuit.fhzheng.multishop.service.impl;

import org.cuit.fhzheng.common.cache.constant.CacheNames;
import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.util.PageUtil;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.multishop.dto.IndexImgDTO;
import org.cuit.fhzheng.multishop.model.IndexImg;
import org.cuit.fhzheng.multishop.mapper.IndexImgMapper;
import org.cuit.fhzheng.multishop.service.IndexImgService;
import org.cuit.fhzheng.multishop.vo.IndexImgVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 轮播图
 *
 * @author YXF
 * @date 2020-11-24 16:38:32
 */
@Service
public class IndexImgServiceImpl implements IndexImgService {

    @Autowired
    private IndexImgMapper indexImgMapper;

    @Override
    public PageVO<IndexImgVO> page(PageDTO pageDTO, IndexImgDTO indexImgDTO) {
        return PageUtil.doPage(pageDTO, () -> indexImgMapper.list(indexImgDTO));
    }

    @Override
    public IndexImgVO getByImgId(Long imgId) {
        return indexImgMapper.getByImgId(imgId);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.INDEX_IMG_KEY, key = "#indexImg.shopId")
    public void save(IndexImg indexImg) {
        indexImgMapper.save(indexImg);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.INDEX_IMG_KEY, key = "#indexImg.shopId")
    public void update(IndexImg indexImg) {
        indexImgMapper.update(indexImg);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.INDEX_IMG_KEY, key = "#shopId")
    public void deleteById(Long imgId, Long shopId) {
        indexImgMapper.deleteByIdAndShopId(imgId, shopId);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.INDEX_IMG_KEY, key = "#shopId", sync = true)
    public List<IndexImgVO> getListByShopId(Long shopId) {
        List<IndexImgVO> listByShopId = indexImgMapper.getListByShopId(shopId);
        return listByShopId;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = CacheNames.INDEX_IMG_KEY, key = "#shopId"),
            @CacheEvict(cacheNames = CacheNames.INDEX_IMG_KEY, key = "0")
    })
    public void deleteBySpuId(Long spuId, Long shopId) {
        indexImgMapper.clearSpuIdBySpuId(spuId);
    }
}
