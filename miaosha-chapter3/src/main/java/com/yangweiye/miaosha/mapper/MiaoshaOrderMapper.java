package com.yangweiye.miaosha.mapper;

import com.yangweiye.miaosha.pojo.MiaoshaOrder;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MiaoshaOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MiaoshaOrder record);

    int insertSelective(MiaoshaOrder record);

    MiaoshaOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoshaOrder record);

    int updateByPrimaryKey(MiaoshaOrder record);

    @Select("select * from ms_miaosha_order where user_id = #{userId} and goods_id = #{goodsId}")
    MiaoshaOrder selectByUserIdAndGoodsId(Long userId, Long goodsId);
}