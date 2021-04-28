package com.yangweiye.miaosha.config;

import com.yangweiye.miaosha.pojo.MiaoshaGoods;
import com.yangweiye.miaosha.service.MiaoshaGoodsService;
import com.yangweiye.miaosha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class MiaoshaInit {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;

    @PostConstruct
    public void redisStock() {
        List<MiaoshaGoods> miaoshaGoods = miaoshaGoodsService.selectAll();
        miaoshaGoods.forEach((result) -> {
            redisUtil.cacheSet("miaosha_goods_stock_" + result.getId(), result.getMiaoshaStcok().toString());
        });
    }
}
