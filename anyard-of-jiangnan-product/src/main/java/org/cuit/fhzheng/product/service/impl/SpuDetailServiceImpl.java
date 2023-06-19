package org.cuit.fhzheng.product.service.impl;

import org.cuit.fhzheng.product.mapper.SpuDetailMapper;
import org.cuit.fhzheng.product.model.SpuDetail;
import org.cuit.fhzheng.product.service.SpuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品详情信息
 *
 * @author
 * @date 2020-10-28 15:27:24
 */
@Service
public class SpuDetailServiceImpl implements SpuDetailService {

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Override
    public void save(SpuDetail spuDetail) {
        spuDetailMapper.save(spuDetail);
    }

    @Override
    public void update(SpuDetail spuDetail) {
        spuDetailMapper.update(spuDetail);
    }

    @Override
    public void deleteById(Long spuId) {
        spuDetailMapper.deleteById(spuId);
    }
}
