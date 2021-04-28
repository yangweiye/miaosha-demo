package com.yangweiye.miaosha.message;

public class OrderMessage {
    private Long userId;
    private Long miaoshaGoodsId;

    public OrderMessage() {
    }

    public OrderMessage(Long userId, Long miaoshaGoodsId) {
        this.userId = userId;
        this.miaoshaGoodsId = miaoshaGoodsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMiaoshaGoodsId() {
        return miaoshaGoodsId;
    }

    public void setMiaoshaGoodsId(Long miaoshaGoodsId) {
        this.miaoshaGoodsId = miaoshaGoodsId;
    }
}
