package com.yangweiye.miaosha.pojo;

import java.util.Date;

public class MiaoshaGoods {
    private Long id;

    private Long goodsId;

    private Integer miaoshaPrice;

    private Integer miaoshaStcok;

    private Date startTime;

    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getMiaoshaPrice() {
        return miaoshaPrice;
    }

    public void setMiaoshaPrice(Integer miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }

    public Integer getMiaoshaStcok() {
        return miaoshaStcok;
    }

    public void setMiaoshaStcok(Integer miaoshaStcok) {
        this.miaoshaStcok = miaoshaStcok;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}