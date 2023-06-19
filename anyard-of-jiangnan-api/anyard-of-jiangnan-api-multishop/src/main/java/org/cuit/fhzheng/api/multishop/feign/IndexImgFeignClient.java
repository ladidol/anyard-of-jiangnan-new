package org.cuit.fhzheng.api.multishop.feign;

import org.cuit.fhzheng.common.feign.FeignInsideAuthConfig;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "anyard-of-jiangnan-multishop",contextId = "indexImg")
public interface IndexImgFeignClient {

    /**
     * 根据商品d删除轮播图信息
     * @param spuId 商品id
     * @param shopId 店鋪id
     * @return void
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/indexImg/deleteBySpuId")
    ServerResponseEntity<Void> deleteBySpuId(@RequestParam("spuId") Long spuId, @RequestParam("shopId") Long shopId);
}
