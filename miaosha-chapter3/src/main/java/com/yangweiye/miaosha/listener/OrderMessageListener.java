package com.yangweiye.miaosha.listener;

import com.yangweiye.miaosha.message.OrderMessage;
import com.yangweiye.miaosha.service.MiaoshaService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "orders_consumer_group", topic = "orders_topic")
public class OrderMessageListener implements RocketMQListener<OrderMessage> {
    @Autowired
    private MiaoshaService miaoshaService;

    @Override
    public void onMessage(OrderMessage orderMessage) {
        System.out.println("userId " + orderMessage.getUserId() + " goodsId " + orderMessage.getMiaoshaGoodsId());
        miaoshaService.createMiaoshaOrder(orderMessage.getUserId(), orderMessage.getMiaoshaGoodsId());
    }
}
