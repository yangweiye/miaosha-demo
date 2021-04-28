package com.yangweiye.miaosha.service;

import com.yangweiye.miaosha.pojo.Order;

public interface MiaoshaService {
    Order createMiaoshaOrder(Long userId, Long miaoshaGoodsId);

    String getErrorMessage();
}
