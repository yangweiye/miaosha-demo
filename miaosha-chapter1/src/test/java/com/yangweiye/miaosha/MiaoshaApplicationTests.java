package com.yangweiye.miaosha;

import com.yangweiye.miaosha.mapper.GoodsMapper;
import com.yangweiye.miaosha.mapper.MiaoshaOrderMapper;
import com.yangweiye.miaosha.pojo.MiaoshaGoods;
import com.yangweiye.miaosha.service.GoodsService;
import com.yangweiye.miaosha.service.MiaoshaGoodsService;
import com.yangweiye.miaosha.service.UserService;
import com.yangweiye.miaosha.vo.MiaoshaGoodsVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MiaoshaApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;

    @Test
    public void contextLoads() {
        List<MiaoshaGoodsVo> miaoshaGoodsVo = miaoshaGoodsService.selectMiaoshaGoodsInfo();
        miaoshaGoodsVo.forEach(System.out::println);
    }

    @Test
    public void insertRecord() {
        MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
        miaoshaGoods.setGoodsId(2L);
        miaoshaGoods.setMiaoshaPrice(100);
        miaoshaGoods.setMiaoshaStcok(10);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        calendar.add(Calendar.DATE, 1);
        miaoshaGoods.setStartTime(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        miaoshaGoods.setEndTime(calendar.getTime());

        miaoshaGoodsService.insert(miaoshaGoods);
    }

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    MiaoshaOrderMapper miaoshaOrderMapper;

    @Test
    public void t() {
        System.out.println(miaoshaOrderMapper.selectByUserIdAndGoodsId(1L, 1L));
    }

    @Autowired
    DataSource dataSource;

    @Test
    public void testMysql() throws SQLException {
        for (int i = 0; i < 100; i++) {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ms_goods");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Object id = resultSet.getLong("id");
        }
    }
}
