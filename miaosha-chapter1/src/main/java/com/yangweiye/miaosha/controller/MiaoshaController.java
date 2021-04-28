package com.yangweiye.miaosha.controller;

import com.yangweiye.miaosha.pojo.MiaoshaOrder;
import com.yangweiye.miaosha.pojo.Order;
import com.yangweiye.miaosha.pojo.User;
import com.yangweiye.miaosha.result.CodeMsg;
import com.yangweiye.miaosha.service.MiaoshaGoodsService;
import com.yangweiye.miaosha.service.MiaoshaOrderService;
import com.yangweiye.miaosha.service.MiaoshaService;
import com.yangweiye.miaosha.service.UserService;
import com.yangweiye.miaosha.vo.MiaoshaGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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

    @PostMapping("/create")
    public String createMiaosha(Model model, @RequestParam("miaoshaGoodsId") Long miaoshaGoodsId) {
        if (!miaoshaGoodsService.checkMiaoshaGoodsStock(miaoshaGoodsId)) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMessage());
            return "miaosha_fail";
        }

        User user = userService.selectByPrimaryKey(1L);
        MiaoshaGoodsVo miaoshaGoodsVo = miaoshaGoodsService.selectMiaoshaGoodsInfoByPrimaryKey(miaoshaGoodsId);
        MiaoshaOrder miaoshaOrder = miaoshaOrderService.selectByUserIdAndGoodsId(user.getId(), miaoshaGoodsVo.getGoodsId());

        if (null != miaoshaOrder) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMessage());
            return "miaosha_fail";
        }

        Order order = miaoshaService.createMiaoshaOrder(user.getId(), miaoshaGoodsId);

        model.addAttribute("goods", miaoshaGoodsVo);
        model.addAttribute("orderInfo", order);

        return "order_detail";
    }
}
