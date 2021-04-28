package com.yangweiye.miaosha.controller;

import com.yangweiye.miaosha.pojo.User;
import com.yangweiye.miaosha.service.MiaoshaGoodsService;
import com.yangweiye.miaosha.service.UserService;
import com.yangweiye.miaosha.vo.MiaoshaGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String goodsList(Model model) {
        List<MiaoshaGoodsVo> miaoshaGoodsVos = miaoshaGoodsService.selectMiaoshaGoodsInfo();
        model.addAttribute("goodsList", miaoshaGoodsVos);
        return "goods_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable(name = "id") Long id) {
        MiaoshaGoodsVo miaoshaGoodsVo = miaoshaGoodsService.selectMiaoshaGoodsInfoByPrimaryKey(id);
        User user = userService.selectByPrimaryKey(1L);

        Integer remainSeconds = -1;
        Integer miaoshaStatus;
        Long startAt = miaoshaGoodsVo.getStartTime().getTime();
        Long endAt = miaoshaGoodsVo.getEndTime().getTime();
        Long now = new Date().getTime();

        if (startAt > now) {
            remainSeconds = ((Number) (startAt - now)).intValue();
            miaoshaStatus = 0;
        } else if (endAt < now) {
            miaoshaStatus = 2;
        } else {
            miaoshaStatus = 1;
        }

        model.addAttribute("user", user);
        model.addAttribute("goods", miaoshaGoodsVo);
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        return "goods_detail";
    }
}
