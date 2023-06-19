package org.cuit.fhzheng.product.feign;

import org.cuit.fhzheng.api.multishop.bo.EsShopDetailBO;
import org.cuit.fhzheng.api.multishop.feign.ShopDetailFeignClient;
import org.cuit.fhzheng.api.product.bo.EsProductBO;
import org.cuit.fhzheng.api.product.feign.ProductFeignClient;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.product.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author
 * @date 2020/11/27
 */
@RestController
public class ProductFeignController implements ProductFeignClient {

    @Autowired
    private SpuService spuService;
    @Autowired
    private ShopDetailFeignClient shopDetailFeignClient;

    @Override
    public ServerResponseEntity<EsProductBO> loadEsProductBO(Long spuId) {
        EsProductBO esProductBO = spuService.loadEsProductBO(spuId);
        // 获取店铺信息
        ServerResponseEntity<EsShopDetailBO> shopDetailResponse = shopDetailFeignClient.getShopByShopId(esProductBO.getShopId());
        EsShopDetailBO shopDetail = shopDetailResponse.getData();
        esProductBO.setShopName(shopDetail.getShopName());
        esProductBO.setShopImg(shopDetail.getShopLogo());
        esProductBO.setShopType(shopDetail.getType());
        if (Objects.isNull(esProductBO.getSaleNum())) {
            esProductBO.setSaleNum(0);
        }
        return ServerResponseEntity.success(esProductBO);
    }

    @Override
    public ServerResponseEntity<List<Long>> getSpuIdsByShopCategoryIds(List<Long> shopCategoryIds) {
        return getSpuIdsBySpuUpdateDTO(shopCategoryIds, null, null, null);
    }

    @Override
    public ServerResponseEntity<List<Long>> getSpuIdsByCategoryIds(List<Long> categoryIds) {
        return getSpuIdsBySpuUpdateDTO(null, categoryIds, null, null);
    }

    @Override
    public ServerResponseEntity<List<Long>> getSpuIdsByBrandId(Long brandId) {
        return getSpuIdsBySpuUpdateDTO(null, null, brandId, null);
    }

    @Override
    public ServerResponseEntity<List<Long>> getSpuIdsByShopId(Long shopId) {
        return getSpuIdsBySpuUpdateDTO(null, null, null, shopId);
    }

    /**
     * 获取spuId列表
     * @param shopCategoryIds 店铺分类id列表
     * @param categoryIds 平台分类Id列表
     * @param brandId 品牌id
     * @param shopId 店铺id
     * @return
     */
    public ServerResponseEntity<List<Long>> getSpuIdsBySpuUpdateDTO(List<Long> shopCategoryIds, List<Long> categoryIds, Long brandId, Long shopId) {
        List<Long> spuIds = spuService.getSpuIdsBySpuUpdateDTO(shopCategoryIds, categoryIds, brandId, shopId);
        return ServerResponseEntity.success(spuIds);
    }
}
