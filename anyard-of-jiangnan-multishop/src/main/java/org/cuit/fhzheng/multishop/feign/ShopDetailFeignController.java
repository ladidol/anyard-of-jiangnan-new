package org.cuit.fhzheng.multishop.feign;

import org.cuit.fhzheng.api.multishop.bo.EsShopDetailBO;
import org.cuit.fhzheng.api.multishop.feign.ShopDetailFeignClient;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.multishop.model.ShopDetail;
import org.cuit.fhzheng.multishop.service.ShopDetailService;
import org.cuit.fhzheng.api.multishop.vo.ShopDetailVO;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author  
 * @date 2020/11/23
 */
@RestController
public class ShopDetailFeignController implements ShopDetailFeignClient {

    @Autowired
    private ShopDetailService shopDetailService;
    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public ServerResponseEntity<String> getShopNameByShopId(Long shopId) {
        ShopDetailVO shopDetail = shopDetailService.getByShopId(shopId);
        if (Objects.isNull(shopDetail)) {
            return ServerResponseEntity.success("");
        }
        return ServerResponseEntity.success(shopDetail.getShopName());
    }

    @Override
    public ServerResponseEntity<EsShopDetailBO> getShopByShopId(Long shopId) {
        ShopDetailVO shopDetail = shopDetailService.getByShopId(shopId);
        if (Objects.isNull(shopDetail)) {
            return ServerResponseEntity.success(new EsShopDetailBO());
        }
        return ServerResponseEntity.success(mapperFacade.map(shopDetail, EsShopDetailBO.class));
    }


    @Override
    public ServerResponseEntity<List<ShopDetailVO>> listByShopIds(List<Long> shopIds) {
        List<ShopDetail> shopDetail = shopDetailService.listByShopIds(shopIds);
        return ServerResponseEntity.success(mapperFacade.mapAsList(shopDetail, ShopDetailVO.class));
    }

    @Override
    public ServerResponseEntity<EsShopDetailBO> shopExtensionData(Long shopId) {
        return ServerResponseEntity.success(shopDetailService.shopExtensionData(shopId));
    }

    @Override
    public ServerResponseEntity<List<ShopDetailVO>> getShopDetailByShopIdAndShopName(List<Long> shopIds, String shopName) {
        return ServerResponseEntity.success(shopDetailService.getShopDetailByShopIdAndShopName(shopIds,shopName));
    }
}
