package com.yangweiye.miaosha.service.impl;

import com.yangweiye.miaosha.mapper.GoodsMapper;
import com.yangweiye.miaosha.pojo.Goods;
import com.yangweiye.miaosha.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsMapper.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Goods record) {
        return goodsMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public Boolean checkGoodsStock(Long goodsId) {
        return goodsMapper.selectGoodsStock(goodsId) > 0;
    }

    @Override
    public Integer reduceGoodsStock(Long goodsId) {
        return goodsMapper.reduceGoodsStock(goodsId);
    }
}
