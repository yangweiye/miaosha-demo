package com.yangweiye.miaosha.controller;

import com.yangweiye.miaosha.pojo.Order;
import com.yangweiye.miaosha.result.Result;
import com.yangweiye.miaosha.service.MiaoshaGoodsService;
import com.yangweiye.miaosha.service.OrderService;
import com.yangweiye.miaosha.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;

    @GetMapping("/detail")
    public Result<OrderDetailVo> getOrderInfo(@RequestParam("orderId") Long orderId) {
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        Order order = orderService.selectByPrimaryKey(orderId);
        if (null == order) {
            return new Result<>(500, "order can not found");
        }
        orderDetailVo.setOrder(order);
        orderDetailVo.setMiaoshaGoodsVo(miaoshaGoodsService.selectMiaoshaGoodsInfoByPrimaryKey(order.getGoodsId()));

        return new Result<>(orderDetailVo);
    }
}
