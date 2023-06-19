package org.cuit.fhzheng.search.feign;


import org.cuit.fhzheng.api.dto.EsPageDTO;
import org.cuit.fhzheng.api.feign.SearchOrderFeignClient;
import org.cuit.fhzheng.api.vo.EsPageVO;
import org.cuit.fhzheng.api.vo.search.EsOrderVO;
import org.cuit.fhzheng.common.dto.OrderSearchDTO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.search.manager.OrderSearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品搜索feign连接
 * @author YXF
 * @date 2020/12/07
 */
@RestController
public class SearchOrderFeignController implements SearchOrderFeignClient {

    @Autowired
    private OrderSearchManager orderSearchManager;


    @Override
    public ServerResponseEntity<EsPageVO<EsOrderVO>> getOrderPage(OrderSearchDTO orderSearch) {
        EsPageDTO pageDTO = new EsPageDTO();
        pageDTO.setPageNum(orderSearch.getPageNum());
        pageDTO.setPageSize(orderSearch.getPageSize());
        return ServerResponseEntity.success(orderSearchManager.pageSearchResult(pageDTO, orderSearch));
    }
}
