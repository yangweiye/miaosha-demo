package com.yangweiye.miaosha.service.impl;

import com.yangweiye.miaosha.mapper.MiaoshaGoodsMapper;
import com.yangweiye.miaosha.pojo.MiaoshaGoods;
import com.yangweiye.miaosha.service.GoodsService;
import com.yangweiye.miaosha.service.MiaoshaGoodsService;
import com.yangweiye.miaosha.vo.MiaoshaGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiaoshaGoodsServiceImpl implements MiaoshaGoodsService {
    @Autowired
    private MiaoshaGoodsMapper miaoshaGoodsMapper;

    @Autowired
    private GoodsService goodsService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return miaoshaGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MiaoshaGoods record) {
        return miaoshaGoodsMapper.insert(record);
    }

    @Override
    public int insertSelective(MiaoshaGoods record) {
        return miaoshaGoodsMapper.insertSelective(record);
    }

    @Override
    public MiaoshaGoods selectByPrimaryKey(Long id) {
        return miaoshaGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MiaoshaGoods record) {
        return miaoshaGoodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MiaoshaGoods record) {
        return miaoshaGoodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MiaoshaGoodsVo> selectMiaoshaGoodsInfo() {
        return miaoshaGoodsMapper.selectMiaoshaGoodsInfo();
    }

    @Override
    public MiaoshaGoodsVo selectMiaoshaGoodsInfoByPrimaryKey(Long id) {
        return miaoshaGoodsMapper.selectMiaoshaGoodsInfoByPrimaryKey(id);
    }

    @Override
    public Boolean checkMiaoshaGoodsStock(Long miaoshaGoodsId) {
        MiaoshaGoods miaoshaGoods = selectByPrimaryKey(miaoshaGoodsId);
        return miaoshaGoodsMapper.selectMiaoshaGoodsStock(miaoshaGoodsId) > 0 && goodsService.checkGoodsStock(miaoshaGoods.getGoodsId());
    }

    @Override
    public Integer reduceMiaoShaGoodsStock(Long id) {
        return miaoshaGoodsMapper.reduceMiaoshaGoodsStock(id);
    }
}
