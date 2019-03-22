package com.zjx.controller;

import com.zjx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 秒杀活动
 * @Author Carson Cheng
 * @Date 2019/3/20 14:53
 * @Version V1.0
 **/
@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private OrderService orderService;

    /**
     * 秒杀
     *
     * @return
     */
    @GetMapping
    public String seckill() {
        return orderService.seckill();
    }
}
