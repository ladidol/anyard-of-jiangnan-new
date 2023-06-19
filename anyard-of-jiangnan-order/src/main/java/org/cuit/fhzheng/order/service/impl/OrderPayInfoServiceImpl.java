package org.cuit.fhzheng.order.service.impl;

import org.cuit.fhzheng.order.model.OrderPayInfo;
import org.cuit.fhzheng.order.mapper.OrderPayInfoMapper;
import org.cuit.fhzheng.order.service.OrderPayInfoService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单支付记录
 *
 * @author
 * @date 2020-12-04 11:27:35
 */
@Service
public class OrderPayInfoServiceImpl implements OrderPayInfoService {

    @Autowired
    private OrderPayInfoMapper orderPayInfoMapper;

    @Override
    public void save(OrderPayInfo orderPayInfo) {
        orderPayInfoMapper.save(orderPayInfo);
    }

    @Override
    public void update(OrderPayInfo orderPayInfo) {
        orderPayInfoMapper.update(orderPayInfo);
    }

    @Override
    public void deleteById(Long payId) {
        orderPayInfoMapper.deleteById(payId);
    }
}
