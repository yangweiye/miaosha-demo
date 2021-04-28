package com.yangweiye.miaosha.service;

import com.yangweiye.miaosha.pojo.MiaoshaGoods;
import com.yangweiye.miaosha.vo.MiaoshaGoodsVo;

import java.util.List;

public interface MiaoshaGoodsService {
    int deleteByPrimaryKey(Long id);

    int insert(MiaoshaGoods record);

    int insertSelective(MiaoshaGoods record);

    MiaoshaGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoshaGoods record);

    int updateByPrimaryKey(MiaoshaGoods record);

    List<MiaoshaGoodsVo> selectMiaoshaGoodsInfo();

    MiaoshaGoodsVo selectMiaoshaGoodsInfoByPrimaryKey(Long id);

    Boolean checkMiaoshaGoodsStock(Long miaoshaGoodsId);

    Integer reduceMiaoShaGoodsStock(Long id);

}
