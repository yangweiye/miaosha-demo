package com.yangweiye.miaosha.vo;

import com.yangweiye.miaosha.pojo.Order;

public class OrderDetailVo {
    private MiaoshaGoodsVo miaoshaGoodsVo;
    private Order order;

    public OrderDetailVo() {
    }

    public OrderDetailVo(MiaoshaGoodsVo miaoshaGoodsVo, Order order) {
        this.miaoshaGoodsVo = miaoshaGoodsVo;
        this.order = order;
    }

    public MiaoshaGoodsVo getMiaoshaGoodsVo() {
        return miaoshaGoodsVo;
    }

    public void setMiaoshaGoodsVo(MiaoshaGoodsVo miaoshaGoodsVo) {
        this.miaoshaGoodsVo = miaoshaGoodsVo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
