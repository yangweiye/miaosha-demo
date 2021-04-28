package com.yangweiye.miaosha.controller;

import com.yangweiye.miaosha.message.OrderMessage;
import com.yangweiye.miaosha.pojo.MiaoshaOrder;
import com.yangweiye.miaosha.pojo.User;
import com.yangweiye.miaosha.result.Result;
import com.yangweiye.miaosha.service.MiaoshaGoodsService;
import com.yangweiye.miaosha.service.MiaoshaOrderService;
import com.yangweiye.miaosha.service.MiaoshaService;
import com.yangweiye.miaosha.service.UserService;
import com.yangweiye.miaosha.util.RedisUtil;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/miaosha")
public class MiaoshaController {
    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private MiaoshaOrderService miaoshaOrderService;

    @Autowired
    private MiaoshaService miaoshaService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

//    public String createMiaosha(Model model, @RequestParam("miaoshaGoodsId") Long miaoshaGoodsId) {
//        if (!miaoshaGoodsService.checkMiaoshaGoodsStock(miaoshaGoodsId)) {
//            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMessage());
//            return "miaosha_fail";
//        }
//
//        User user = userService.selectByPrimaryKey(1L);
//        MiaoshaGoodsVo miaoshaGoodsVo = miaoshaGoodsService.selectMiaoshaGoodsInfoByPrimaryKey(miaoshaGoodsId);
//        MiaoshaOrder miaoshaOrder = miaoshaOrderService.selectByUserIdAndGoodsId(user.getId(), miaoshaGoodsVo.getGoodsId());
//
//        if (null != miaoshaOrder) {
//            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMessage());
//            return "miaosha_fail";
//        }
//
//        Order order = miaoshaService.createMiaoshaOrder(user.getId(), miaoshaGoodsId);
//
//        model.addAttribute("goods", miaoshaGoodsVo);
//        model.addAttribute("orderInfo", order);
//
//        return "order_detail";
//    }
//
//    public Result<Long> createMiaoshaOrder(@RequestParam("miaoshaGoodsId") Long miaoshaGoodsId) {
//        if (Integer.parseInt(redisUtil.cacheGet("miaosha_goods_stock_" + miaoshaGoodsId)) <= 0) {
//            //return new Result<>(500, "库存不足");
//        }
//
//        User user = userService.selectByPrimaryKey(1L);
//        MiaoshaGoodsVo miaoshaGoodsVo = miaoshaGoodsService.selectMiaoshaGoodsInfoByPrimaryKey(miaoshaGoodsId);
//        MiaoshaOrder miaoshaOrder = miaoshaOrderService.selectByUserIdAndGoodsId(user.getId(), miaoshaGoodsVo.getGoodsId());
//
//        if (null != miaoshaOrder) {
//            return new Result<>(500, "你已参与过此次秒杀活动了");
//        }
//
//        Order order = miaoshaService.createMiaoshaOrder(user.getId(), miaoshaGoodsId);
//        if (null == order) {
//            return new Result<>(500, miaoshaService.getErrorMessage());
//        }
//        return new Result<>(order.getId());
//    }

    @PostMapping("/create")
    public Result<Integer> pushMiaoshaMQ(@RequestParam("miaoshaGoodsId") Long miaoshaGoodsId) {
        User user = userService.selectByPrimaryKey(1L);
        String orderCache = redisUtil.cacheGet("miaosha_qualification_" + user.getId() + "_" + miaoshaGoodsId);
        if (null != orderCache) {
            return new Result<>(500, "参与过了");
        }

        if (redisUtil.cacheDecr("miaosha_goods_stock_" + miaoshaGoodsId) <= 0) {
            return new Result<>(500, "库存不足");
        }
        redisUtil.cacheSet("miaosha_qualification_" + user.getId() + "_" + miaoshaGoodsId, "1");

        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setUserId(user.getId());
        orderMessage.setMiaoshaGoodsId(miaoshaGoodsId);

        rocketMQTemplate.convertAndSend("orders_topic", orderMessage);
        return new Result<>(0);
    }

    @GetMapping("/result")
    public Result<Long> miaoshaResult(@RequestParam("miaoshaGoodsId") Long miaoshaGoodsId) throws IOException, ClassNotFoundException {
        User user = userService.selectByPrimaryKey(1L);

        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        MiaoshaOrder miaoshaOrder = (MiaoshaOrder) redisTemplate.opsForValue().get("miaosha_order_info_" + user.getId() + "_" + miaoshaGoodsId);

        if (null != miaoshaOrder) {
            return new Result<>(miaoshaOrder.getOrderId());
        }

        if (null != redisUtil.cacheGet("miaosha_goods_over_" + miaoshaGoodsId))
            return new Result<>(-1L);

        return new Result<>(0L);
    }
}
