package com.zjx.mapper;

import com.zjx.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/3/20 12:44
 * @Version V1.0
 **/
@Repository
public interface OrderMapper {

    List<Order> queryOrders(Map<String, Object> params);

    int addOrder(Order order);

    Order getById(Integer id);

    int deleteById(Integer id);

    int updateById(Order order);
}
