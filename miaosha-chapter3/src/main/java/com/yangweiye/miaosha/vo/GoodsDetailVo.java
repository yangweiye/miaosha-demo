package com.yangweiye.miaosha.vo;

import com.yangweiye.miaosha.pojo.User;

public class GoodsDetailVo {
    private Integer miaoshaStatus;
    private Integer remainSeconds;
    private User user;
    private MiaoshaGoodsVo miaoshaGoodsVo;

    public GoodsDetailVo() {
    }

    public GoodsDetailVo(Integer miaoshaStatus, Integer remainSeconds, User user, MiaoshaGoodsVo miaoshaGoodsVo) {
        this.miaoshaStatus = miaoshaStatus;
        this.remainSeconds = remainSeconds;
        this.user = user;
        this.miaoshaGoodsVo = miaoshaGoodsVo;
    }

    public Integer getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(Integer miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public Integer getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(Integer remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MiaoshaGoodsVo getMiaoshaGoodsVo() {
        return miaoshaGoodsVo;
    }

    public void setMiaoshaGoodsVo(MiaoshaGoodsVo miaoshaGoodsVo) {
        this.miaoshaGoodsVo = miaoshaGoodsVo;
    }
}
