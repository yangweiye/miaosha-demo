package com.yangweiye.miaosha.service.impl;

import com.yangweiye.miaosha.pojo.MiaoshaOrder;
import com.yangweiye.miaosha.pojo.Order;
import com.yangweiye.miaosha.service.*;
import com.yangweiye.miaosha.util.RedisUtil;
import com.yangweiye.miaosha.vo.MiaoshaGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class MiaoshaServiceImpl implements MiaoshaService {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MiaoshaOrderService miaoshaOrderService;
    @Autowired
    private RedisUtil redisUtil;

    private String errorMessage;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @CachePut(cacheNames = "redisCache", key = "'order_info_'+#userId+'_'+#miaoshaGoodsId")
    public Order createMiaoshaOrder(Long userId, Long miaoshaGoodsId) {
        if (redisUtil.cacheDecr("miaosha_goods_stock_" + miaoshaGoodsId) <= 0) {
            this.errorMessage = "库存为zero";
            return null;
        }

        MiaoshaGoodsVo miaoshaGoodsVo = miaoshaGoodsService.selectMiaoshaGoodsInfoByPrimaryKey(miaoshaGoodsId);
        goodsService.reduceGoodsStock(miaoshaGoodsVo.getGoodsId());
        miaoshaGoodsService.reduceMiaoShaGoodsStock(miaoshaGoodsVo.getMiaoshaGoodsId());

        Order order = new Order();
        order.setCreateTime(new Date());
        order.setGoodsId(miaoshaGoodsVo.getGoodsId());
        order.setOrderChannel((byte) 1);
        order.setGoodsCount(1);
        order.setGoodsName(miaoshaGoodsVo.getGoodsName());
        order.setGoodsPrice(miaoshaGoodsVo.getMiaoshaPrice());
        order.setDeliveryAddrId(0L);
        order.setUserId(userId);
        order.setStatus((byte) 0);
        order.setPayTime(null);

        orderService.insert(order);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setOrderId(order.getId());
        miaoshaOrder.setGoodsId(miaoshaGoodsVo.getGoodsId());
        miaoshaOrder.setUserId(userId);

        miaoshaOrderService.insert(miaoshaOrder);

        return order;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
