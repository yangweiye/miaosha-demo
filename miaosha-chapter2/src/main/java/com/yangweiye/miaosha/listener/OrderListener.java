package com.yangweiye.miaosha.listener;

import com.yangweiye.miaosha.pojo.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RocketMQMessageListener(consumerGroup = "orders_consumer_group", topic = "orders_topic")
public class OrderListener implements RocketMQListener<String> {
    @PostConstruct
    public void tt(){
        System.out.println("11111111111111111111111111111111111111111111111111111");
    }
    @Override
    public void onMessage(String o) {
        System.out.println(o);
    }
}
