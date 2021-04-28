package com.yangweiye.miaosha.mapper;

import com.yangweiye.miaosha.pojo.Goods;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    @Select("select goods_stock from ms_goods where id = #{goodsId}")
    Integer selectGoodsStock(Long goodsId);

    @Update("update ms_goods set goods_stock = goods_stock - 1 where id = #{goodsId}")
    Integer reduceGoodsStock(Long goodsId);
}