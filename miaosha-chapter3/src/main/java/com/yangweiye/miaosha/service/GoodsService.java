package com.yangweiye.miaosha.service;

import com.yangweiye.miaosha.pojo.Goods;

public interface GoodsService {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    Boolean checkGoodsStock(Long goodsId);

    Integer reduceGoodsStock(Long goodsId);
}
