package org.cuit.fhzheng.product.feign;

import org.cuit.fhzheng.api.product.dto.SkuStockLockDTO;
import org.cuit.fhzheng.api.product.feign.SkuStockLockFeignClient;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.product.service.SkuStockLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author  
 * @date 2020/12/8
 */
@RestController
public class SkuStockLockFeignController implements SkuStockLockFeignClient {


    @Autowired
    private SkuStockLockService skuStockLockService;

    @Override
    public ServerResponseEntity<Void> lock(List<SkuStockLockDTO> skuStockLocksParam) {
        return skuStockLockService.lock(skuStockLocksParam);
    }
}
