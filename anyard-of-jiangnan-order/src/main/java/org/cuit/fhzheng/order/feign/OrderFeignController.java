package org.cuit.fhzheng.order.feign;

import cn.hutool.core.collection.CollectionUtil;
import org.cuit.fhzheng.api.order.bo.EsOrderBO;
import org.cuit.fhzheng.api.order.bo.OrderSimpleAmountInfoBO;
import org.cuit.fhzheng.api.order.bo.OrderStatusBO;
import org.cuit.fhzheng.api.order.constant.OrderStatus;
import org.cuit.fhzheng.api.order.feign.OrderFeignClient;
import org.cuit.fhzheng.api.order.vo.OrderAmountVO;
import org.cuit.fhzheng.common.response.ResponseEnum;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author
 * @date 2020/12/25
 */
@RestController
public class OrderFeignController implements OrderFeignClient {


    @Autowired
    private OrderService orderService;

    @Override
    public ServerResponseEntity<OrderAmountVO> getOrdersAmountAndIfNoCancel(List<Long> orderIds) {
        List<OrderStatusBO> orderStatus = orderService.getOrdersStatus(orderIds);
        if (CollectionUtil.isEmpty(orderStatus)) {
            return ServerResponseEntity.fail(ResponseEnum.ORDER_NOT_EXIST);
        }

        for (OrderStatusBO statusBO : orderStatus) {
            // 订单已关闭
            if (statusBO.getStatus() == null || Objects.equals(statusBO.getStatus(), OrderStatus.CLOSE.value())) {
                return ServerResponseEntity.fail(ResponseEnum.ORDER_EXPIRED);
            }
        }

        OrderAmountVO orderAmountVO = orderService.getOrdersActualAmount(orderIds);
        return ServerResponseEntity.success(orderAmountVO);
    }

    @Override
    public ServerResponseEntity<List<OrderStatusBO>> getOrdersStatus(List<Long> orderIds) {
        List<OrderStatusBO> orderStatusList = orderService.getOrdersStatus(orderIds);
        return ServerResponseEntity.success(orderStatusList);
    }

    @Override
    public ServerResponseEntity<List<OrderSimpleAmountInfoBO>> getOrdersSimpleAmountInfo(List<Long> orderIds) {
        return ServerResponseEntity.success(orderService.getOrdersSimpleAmountInfo(orderIds));
    }

    @Override
    public ServerResponseEntity<EsOrderBO> getEsOrder(Long orderId) {
        EsOrderBO esOrderBO = orderService.getEsOrder(orderId);
        return ServerResponseEntity.success(esOrderBO);
    }

    @Override
    public ServerResponseEntity<Void> updateOrderState(List<Long> orderIds) {
        return null;
    }
}
