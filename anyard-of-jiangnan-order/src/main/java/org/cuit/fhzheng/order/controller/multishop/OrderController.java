package org.cuit.fhzheng.order.controller.multishop;

import org.cuit.fhzheng.api.feign.SearchOrderFeignClient;
import org.cuit.fhzheng.api.order.constant.OrderStatus;
import org.cuit.fhzheng.api.order.dto.DeliveryOrderDTO;
import org.cuit.fhzheng.api.vo.EsPageVO;
import org.cuit.fhzheng.api.vo.search.EsOrderVO;
import org.cuit.fhzheng.common.dto.OrderSearchDTO;
import org.cuit.fhzheng.common.response.ResponseEnum;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.cuit.fhzheng.order.model.Order;
import org.cuit.fhzheng.order.model.OrderAddr;
import org.cuit.fhzheng.order.service.OrderAddrService;
import org.cuit.fhzheng.order.service.OrderService;
import org.cuit.fhzheng.order.vo.OrderAddrVO;
import org.cuit.fhzheng.order.vo.OrderVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author   on 2018/09/15.
 */
@RestController("multishopOrderController")
@Controller
@RequestMapping("/m/order")
@Tag(name = "multishop-订单接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private SearchOrderFeignClient searchOrderFeignClient;

    @Autowired
    private OrderAddrService orderAddrService;
    /**
     * 分页获取
     */
    @GetMapping("/page")
    @Operation(summary = "分页获取订单详情")
    public ServerResponseEntity<EsPageVO<EsOrderVO>> page(OrderSearchDTO orderSearchDTO) {
        Long shopId = AuthUserContext.get().getTenantId();
        orderSearchDTO.setShopId(shopId);
        return searchOrderFeignClient.getOrderPage(orderSearchDTO);
    }

    /**
     * 获取信息
     */
    @GetMapping("/order_info/{orderId}")
    @Operation(summary = "根据id获取订单详情")
    public ServerResponseEntity<OrderVO> info(@PathVariable("orderId") Long orderId) {
        // 订单和订单项
        Order order = orderService.getOrderAndOrderItemData(orderId, AuthUserContext.get().getTenantId());
        // 详情用户收货地址
        OrderAddr orderAddr = orderAddrService.getByOrderAddrId(order.getOrderAddrId());
        order.setOrderAddr(mapperFacade.map(orderAddr, OrderAddr.class));
        OrderVO orderVO = mapperFacade.map(order, OrderVO.class);
        return ServerResponseEntity.success(orderVO);
    }
    /**
     * 获取订单用户下单地址
     */
    @GetMapping("/order_addr/{orderAddrId}")
    @Operation(summary = "获取订单用户下单地址")
    public ServerResponseEntity<OrderAddrVO> getOrderAddr(@PathVariable("orderAddrId") Long orderAddrId) {
        OrderAddr orderAddr = orderAddrService.getByOrderAddrId(orderAddrId);
        return ServerResponseEntity.success(mapperFacade.map(orderAddr, OrderAddrVO.class));
    }

    /**
     * 订单项待发货数量查询
     */
    @GetMapping("/order_item_and_address/{orderId}")
    @Operation(summary = "订单项待发货数量查询")
    public ServerResponseEntity<OrderVO> getOrderItemAndAddress(@PathVariable("orderId") Long orderId) {
        // 订单和订单项
        Order order = orderService.getOrderAndOrderItemData(orderId, AuthUserContext.get().getTenantId());
        OrderVO orderVO = mapperFacade.map(order, OrderVO.class);
        // 用户收货地址
        OrderAddr orderAddr = orderAddrService.getByOrderAddrId(order.getOrderAddrId());
        orderVO.setOrderAddr(mapperFacade.map(orderAddr, OrderAddrVO.class));
        return ServerResponseEntity.success(orderVO);
    }

    /**
     * 发货
     */
    @PostMapping("/delivery")
    @Operation(summary = "发货")
    public ServerResponseEntity<Void> delivery(@Valid @RequestBody DeliveryOrderDTO deliveryOrderParam) {
        OrderVO order = orderService.getOrderByOrderId(deliveryOrderParam.getOrderId());
        // 订单不在支付状态
        if (!Objects.equals(order.getStatus(), OrderStatus.PADYED.value())){
            return ServerResponseEntity.fail(ResponseEnum.ORDER_NOT_PAYED);
        }
        orderService.delivery(deliveryOrderParam);
        return ServerResponseEntity.success();
    }

}
