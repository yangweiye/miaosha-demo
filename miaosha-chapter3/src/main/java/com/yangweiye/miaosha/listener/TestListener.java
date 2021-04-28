package com.yangweiye.miaosha.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "test_consumer_group", topic = "orders_topic")
public class TestListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String o) {
        System.out.println(o);
    }
}
