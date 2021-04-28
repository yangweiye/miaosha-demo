package com.yangweiye.miaosha.service;

import com.yangweiye.miaosha.pojo.Order;

public interface OrderService {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
