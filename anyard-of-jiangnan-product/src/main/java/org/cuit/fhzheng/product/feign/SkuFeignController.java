package org.cuit.fhzheng.product.feign;

import org.cuit.fhzheng.api.product.feign.SkuFeignClient;
import org.cuit.fhzheng.api.product.vo.SkuVO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.product.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  
 * @date 2020/12/8
 */
@RestController
public class SkuFeignController implements SkuFeignClient {

    @Autowired
    private SkuService skuService;


    @Override
    public ServerResponseEntity<SkuVO> getById(Long skuId) {
        return ServerResponseEntity.success(skuService.getSkuBySkuId(skuId));
    }
}
