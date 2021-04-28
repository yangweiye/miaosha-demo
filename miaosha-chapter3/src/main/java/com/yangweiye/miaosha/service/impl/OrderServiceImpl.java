package com.yangweiye.miaosha.service.impl;

import com.yangweiye.miaosha.mapper.OrderMapper;
import com.yangweiye.miaosha.pojo.Order;
import com.yangweiye.miaosha.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    public int insertSelective(Order record) {
        return orderMapper.insertSelective(record);
    }

    @Override
    @Cacheable(cacheNames = "redisCache", key = "'order_info_'+#id")
    public Order selectByPrimaryKey(Long id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        System.out.println(order);
        return order;
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }
}
