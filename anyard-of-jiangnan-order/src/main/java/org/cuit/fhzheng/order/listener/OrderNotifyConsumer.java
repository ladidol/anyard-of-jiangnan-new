package org.cuit.fhzheng.order.listener;

import org.cuit.fhzheng.common.exception.anyardOfJiangnanException;
import org.cuit.fhzheng.common.order.bo.PayNotifyBO;
import org.cuit.fhzheng.common.response.ResponseEnum;
import org.cuit.fhzheng.common.rocketmq.config.RocketMqConstant;
import org.cuit.fhzheng.common.util.Json;
import org.cuit.fhzheng.order.service.OrderService;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author
 * @date  2023/1/7
 */
@Component
@RocketMQMessageListener(topic = RocketMqConstant.ORDER_NOTIFY_TOPIC,consumerGroup = RocketMqConstant.ORDER_NOTIFY_TOPIC)
public class OrderNotifyConsumer implements RocketMQListener<PayNotifyBO> {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RocketMQTemplate orderNotifyStockTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(OrderNotifyConsumer.class);

    @Override
    public void onMessage(PayNotifyBO message) {
        LOG.info("订单回调开始... message: " + Json.toJsonString(message));
        orderService.updateByToPaySuccess(message.getOrderIds());
        // 发送消息，订单支付成功 通知库存扣减
        SendStatus sendStockStatus = orderNotifyStockTemplate.syncSend(RocketMqConstant.ORDER_NOTIFY_STOCK_TOPIC, new GenericMessage<>(message)).getSendStatus();
        if (!Objects.equals(sendStockStatus,SendStatus.SEND_OK)) {
            throw new anyardOfJiangnanException(ResponseEnum.EXCEPTION);
        }
    }
}
