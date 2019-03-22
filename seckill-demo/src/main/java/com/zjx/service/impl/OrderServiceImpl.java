package com.zjx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjx.domain.Order;
import com.zjx.mapper.OrderMapper;
import com.zjx.service.OrderService;
import com.zjx.util.RedisUtils;
import io.lettuce.core.RedisClient;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/3/20 12:43
 * @Version V1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageInfo<Order> queryOrders(Map<String, Object> params) {
        int curPage = (Integer) params.get("curPage");
        int pageSize = (Integer) params.get("pageSize");
        PageHelper.startPage(curPage, pageSize);
        List<Order> list = orderMapper.queryOrders(params);
        return new PageInfo<>(list);
    }

    @Override
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public Order getById(Integer id) {
        return orderMapper.getById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return orderMapper.deleteById(id);
    }

    @Override
    public int updateById(Order order) {
        return orderMapper.updateById(order);
    }

    private ThreadLocal<String> uuidKey = new ThreadLocal<String>();

    @Autowired
    private RedissonClient redissonClient;

    /**
     * redis实现秒杀
     * @return
     */
    @Override
    @Transactional
    public String seckill() {
        uuidKey.set(UUID.randomUUID().toString().replace("-", ""));
        // 获取锁
        Boolean exist = redisUtils.setIfAbsent("sotckKey", "stockValue", 5L, TimeUnit.SECONDS);
        if (!exist) {
            System.out.println("获取锁失败, 请稍后再试！");
            return "抢购失败, 请稍后再试！";
        }
        try {
            // 1. 扣库存
            Integer stock = Integer.valueOf(redisUtils.get("stock"));
            if (stock <= 0) {
                System.out.println("商品被抢完了");
                return "商品被抢完了";
            }
            redisUtils.set("stock", String.valueOf(stock - 1));
            System.out.println(uuidKey.get() + "抢购成功！ 当前库存为：" + (stock - 1));

            // 2. 添加到订单表
            Order order = new Order(uuidKey.get(), "Thinking in Java", new BigDecimal("50.00"), 1);
            addOrder(order);
        } catch (Exception e) {
            throw e;
        } finally {
            redisUtils.remove("sotckKey");
            System.out.println("删除锁成功");
        }
        return "抢购成功！";
    }


    /**
     * redisson实现秒杀
     * @return
     */
    @Transactional
    @Override
    public String seckillRedisson() {
        uuidKey.set(UUID.randomUUID().toString().replace("-", ""));
        RLock myLock = redissonClient.getLock("myLock");
        if (!myLock.tryLock()) {
            System.out.println("获取锁失败, 请稍后再试！");
            return "抢购失败, 请稍后再试！";
        }
        try {
            // 1. 扣库存
            Integer stock = Integer.valueOf(redisUtils.get("stock"));
            if (stock <= 0) {
                System.out.println("商品被抢完了");
                return "商品被抢完了";
            }
            redisUtils.set("stock", String.valueOf(stock - 1));
            System.out.println(uuidKey.get() + "抢购成功！ 当前库存为：" + (stock - 1));

            // 2. 添加到订单表
            Order order = new Order(uuidKey.get(), "Thinking in Java", new BigDecimal("50.00"), 1);
            addOrder(order);
        } catch (Exception e) {
            throw e;
        } finally {
            myLock.unlock();
            System.out.println("删除锁成功");
        }
        return  "抢购成功！";
    }
}
