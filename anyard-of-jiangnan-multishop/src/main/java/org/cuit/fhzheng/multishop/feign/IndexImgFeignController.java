package org.cuit.fhzheng.multishop.feign;

import org.cuit.fhzheng.api.multishop.feign.IndexImgFeignClient;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.multishop.service.IndexImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lth
 * @Date  2023/7/8 11:12
 */
@RestController
public class IndexImgFeignController implements IndexImgFeignClient {

    @Autowired
    private IndexImgService indexImgService;

    @Override
    public ServerResponseEntity<Void> deleteBySpuId(Long spuId, Long shopId) {
        indexImgService.deleteBySpuId(spuId, shopId);
        return ServerResponseEntity.success();
    }
}
