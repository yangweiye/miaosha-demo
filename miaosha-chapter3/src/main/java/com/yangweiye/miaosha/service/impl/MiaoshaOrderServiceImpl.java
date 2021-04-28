package com.yangweiye.miaosha.service.impl;

import com.yangweiye.miaosha.mapper.MiaoshaOrderMapper;
import com.yangweiye.miaosha.pojo.MiaoshaOrder;
import com.yangweiye.miaosha.service.MiaoshaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService {
    @Autowired
    private MiaoshaOrderMapper miaoshaOrderMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return miaoshaOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MiaoshaOrder record) {
        return miaoshaOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(MiaoshaOrder record) {
        return miaoshaOrderMapper.insertSelective(record);
    }

    @Override
    public MiaoshaOrder selectByPrimaryKey(Long id) {
        return miaoshaOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MiaoshaOrder record) {
        return miaoshaOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MiaoshaOrder record) {
        return miaoshaOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    @CachePut(cacheNames = "redisCache", key = "'miaosha_order_info_'+#result.userId+'_'+#result.goodsId")
    public MiaoshaOrder insertMiaoshaOrder(MiaoshaOrder record) {
        miaoshaOrderMapper.insert(record);
        return record;
    }

    @Override
//    @Cacheable(cacheNames = "redisCache", key = "'miaosha_order_info_'+#userId+'_'+#goodsId")
    public MiaoshaOrder selectByUserIdAndGoodsId(Long userId, Long goodsId) {
        return miaoshaOrderMapper.selectByUserIdAndGoodsId(userId, goodsId);
    }
}
