package org.cuit.fhzheng.api.feign;

import org.cuit.fhzheng.api.vo.EsPageVO;
import org.cuit.fhzheng.api.vo.search.EsOrderVO;
import org.cuit.fhzheng.common.dto.OrderSearchDTO;
import org.cuit.fhzheng.common.feign.FeignInsideAuthConfig;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 订单搜索
 * @author
 * @date  2023/02/05
 */
@FeignClient(value = "anyard-of-jiangnan-search",contextId = "searchOrder")
public interface SearchOrderFeignClient {


    /**
     * 订单搜索
     * @param orderSearch 订单搜索参数
     * @return 订单列表
     */
    @PutMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/searchOrder/getOrderPage")
    ServerResponseEntity<EsPageVO<EsOrderVO>> getOrderPage(@RequestBody OrderSearchDTO orderSearch);

}
