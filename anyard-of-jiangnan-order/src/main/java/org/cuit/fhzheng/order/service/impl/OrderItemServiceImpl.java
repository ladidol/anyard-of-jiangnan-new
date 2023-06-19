package org.cuit.fhzheng.order.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.cuit.fhzheng.order.model.OrderItem;
import org.cuit.fhzheng.order.mapper.OrderItemMapper;
import org.cuit.fhzheng.order.service.OrderItemService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 订单项
 *
 * @author
 * @date 2020-12-04 11:27:35
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public void save(OrderItem orderItem) {
        orderItemMapper.save(orderItem);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.update(orderItem);
    }

    @Override
    public void deleteById(Long orderItemId) {
        orderItemMapper.deleteById(orderItemId);
    }

    @Override
    public List<OrderItem> listOrderItemsByOrderId(Long orderId) {
        return orderItemMapper.listOrderItemsByOrderId(orderId);
    }

    @Override
    public void saveBatch(List<OrderItem> orderItems) {
        if (CollUtil.isEmpty(orderItems)) {
            return;
        }
        orderItemMapper.saveBatch(orderItems);
    }

    @Override
    public List<String> getSpuNameListByOrderIds(long[] orderIdList) {
        return orderItemMapper.getSpuNameListByOrderIds(orderIdList);
    }

    @Override
    public Integer countByOrderId(Long orderId) {
        return orderItemMapper.countByOrderId(orderId);
    }


}
