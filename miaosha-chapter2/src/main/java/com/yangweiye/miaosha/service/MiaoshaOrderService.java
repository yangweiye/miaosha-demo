package com.yangweiye.miaosha.service;

import com.yangweiye.miaosha.pojo.MiaoshaOrder;

public interface MiaoshaOrderService {
    int deleteByPrimaryKey(Long id);

    int insert(MiaoshaOrder record);

    int insertSelective(MiaoshaOrder record);

    MiaoshaOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoshaOrder record);

    int updateByPrimaryKey(MiaoshaOrder record);

    MiaoshaOrder selectByUserIdAndGoodsId(Long userId, Long goodsId);
}
