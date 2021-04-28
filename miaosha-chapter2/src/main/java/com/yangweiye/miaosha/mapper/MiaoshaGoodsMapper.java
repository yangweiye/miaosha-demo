package com.yangweiye.miaosha.mapper;

import com.yangweiye.miaosha.pojo.MiaoshaGoods;
import com.yangweiye.miaosha.vo.MiaoshaGoodsVo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiaoshaGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MiaoshaGoods record);

    int insertSelective(MiaoshaGoods record);

    MiaoshaGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoshaGoods record);

    int updateByPrimaryKey(MiaoshaGoods record);

    @Select("select *,mg.id as miaosha_goods_id from ms_miaosha_goods as mg left join ms_goods as g on mg.goods_id = g.id")
    List<MiaoshaGoodsVo> selectMiaoshaGoodsInfo();

    @Select("select *,mg.id as miaosha_goods_id from ms_miaosha_goods as mg left join ms_goods as g on mg.goods_id = g.id where mg.id = #{id}")
    MiaoshaGoodsVo selectMiaoshaGoodsInfoByPrimaryKey(Long id);

    @Select("select miaosha_stcok from ms_miaosha_goods where id = #{goodsId}")
    Integer selectMiaoshaGoodsStock(Long miaoshaGoodsId);

    @Update("update ms_miaosha_goods set miaosha_stcok = miaosha_stcok - 1 where id = #{id}")
    Integer reduceMiaoshaGoodsStock(Long id);

    @Select("select * from ms_miaosha_goods")
    List<MiaoshaGoods> selectAll();
}