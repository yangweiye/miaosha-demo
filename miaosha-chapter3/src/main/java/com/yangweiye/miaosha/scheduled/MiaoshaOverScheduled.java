package com.yangweiye.miaosha.scheduled;

import com.yangweiye.miaosha.pojo.MiaoshaGoods;
import com.yangweiye.miaosha.service.MiaoshaGoodsService;
import com.yangweiye.miaosha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MiaoshaOverScheduled {
    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;

    @Autowired
    private RedisUtil redisUtil;

    @Scheduled(fixedRate = 3000)
    public void checkMiaoshaOver() {
        List<MiaoshaGoods> miaoshaGoods = miaoshaGoodsService.selectAll();
        miaoshaGoods.forEach((r) -> {
            if (r.getMiaoshaStcok() <= 0) {
                redisUtil.cacheSet("miaosha_goods_over_" + r.getId(), "1");
            }
        });
    }
}
