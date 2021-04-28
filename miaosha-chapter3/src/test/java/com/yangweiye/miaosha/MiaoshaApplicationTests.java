package com.yangweiye.miaosha;

import com.yangweiye.miaosha.mapper.GoodsMapper;
import com.yangweiye.miaosha.mapper.MiaoshaOrderMapper;
import com.yangweiye.miaosha.pojo.MiaoshaGoods;
import com.yangweiye.miaosha.pojo.MiaoshaOrder;
import com.yangweiye.miaosha.pojo.User;
import com.yangweiye.miaosha.service.GoodsService;
import com.yangweiye.miaosha.service.MiaoshaGoodsService;
import com.yangweiye.miaosha.service.UserService;
import com.yangweiye.miaosha.vo.MiaoshaGoodsVo;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.*;
import java.math.BigInteger;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Test
    public void t() {
//        String miaosha_order_info_1_2 = redisTemplate.opsForValue().get("miaosha_order_info_1_2");
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        MiaoshaOrder miaosha_order_info_1_2 = (MiaoshaOrder) redisTemplate.opsForValue().get("miaosha_order_info_1_2");
        System.out.println(miaosha_order_info_1_2.getOrderId());
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

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void testRocketmq() throws UnsupportedEncodingException, InterruptedException {
        User user = new User();
        user.setId(1L);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);

            rocketMQTemplate.syncSend("orders_topic", user.toString());
        }

    }

    @Test
    public void testDeserializeTest() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        BigInteger bi = new BigInteger("0");
        oos.writeObject(bi);
        String str = baos.toString();
        System.out.println(str);
        ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new ByteArrayInputStream(str.getBytes())));
        Object obj = ois.readObject();
        System.out.println(obj);
    }

    @Test
    public void testDeserialize() throws IOException, ClassNotFoundException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
        String miaosha_order_info_1_2 = stringRedisTemplate.opsForValue().get("miaosha_order_info_1_2");
//        oos.writeObject(miaosha_order_info_1_2);
//        byte[] str = baos.toByteArray();
//        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(miaosha_order_info_1_2.getBytes())));
//        Object obj = ois.readObject();
//        System.out.println(obj);
    }
}
