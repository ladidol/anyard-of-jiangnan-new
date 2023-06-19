package org.cuit.fhzheng.product.service.impl;

import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.util.PageUtil;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.product.mapper.SpuExtensionMapper;
import org.cuit.fhzheng.product.model.SpuExtension;
import org.cuit.fhzheng.product.service.SpuExtensionService;
import org.cuit.fhzheng.product.vo.SpuExtensionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author
 * @date 2020-11-11 13:49:06
 */
@Service
public class SpuExtensionServiceImpl implements SpuExtensionService {

    @Autowired
    private SpuExtensionMapper spuExtensionMapper;

    @Override
    public PageVO<SpuExtensionVO> page(PageDTO pageDTO) {
        return PageUtil.doPage(pageDTO, () -> spuExtensionMapper.list());
    }

    @Override
    public SpuExtensionVO getBySpuExtendId(Long spuExtendId) {
        return spuExtensionMapper.getBySpuExtendId(spuExtendId);
    }

    @Override
    public void save(SpuExtension spuExtension) {
        spuExtensionMapper.save(spuExtension);
    }

    @Override
    public void updateStock(Long spuId, Integer count) {
        spuExtensionMapper.updateStock(spuId,count);
    }

    @Override
    public void deleteById(Long spuId) {
        spuExtensionMapper.deleteById(spuId);
    }

    @Override
    public SpuExtension getBySpuId(Long spuId) {
        return spuExtensionMapper.getBySpuId(spuId);
    }
}
