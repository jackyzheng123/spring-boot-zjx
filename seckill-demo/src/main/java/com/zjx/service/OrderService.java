package com.zjx.service;

import com.github.pagehelper.PageInfo;
import com.zjx.domain.Order;

import java.util.Map;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/3/20 12:40
 * @Version V1.0
 **/
public interface OrderService {

    PageInfo<Order> queryOrders(Map<String, Object> params);

    int addOrder(Order order);

    Order getById(Integer id);

    int deleteById(Integer id);

    int updateById(Order order);

    String seckill();

    String seckillRedisson();

}
