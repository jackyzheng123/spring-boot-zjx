package com.zjx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjx.domain.Order;
import com.zjx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/3/20 12:38
 * @Version V1.0
 **/

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public PageInfo<Order> queryOrders(@RequestBody Map<String, Object> params){
        return  orderService.queryOrders(params);
    }

    @PostMapping
    public String addOrder(@RequestBody Order order){
        orderService.addOrder(order);
        return "下单成功！";
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") Integer id){
        Order order = orderService.getById(id);
        return order;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        orderService.deleteById(id);
        return "删除成功！";
    }

    @PutMapping
    public String updateById(@RequestBody Order order){
        Order entity = orderService.getById(order.getId());
        if (null == entity) {
            return "订单不存在";
        }
        orderService.updateById(order);
        return "修改成功！";
    }

}
