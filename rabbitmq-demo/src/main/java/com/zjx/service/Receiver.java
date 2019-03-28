package com.zjx.service;

import com.zjx.entity.Order;
import com.zjx.mapper.OrderMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 一对多，一个发送者发送消息，多个接受者接受同一个消息
 * @Author Carson Cheng
 * @Date 2019/3/27 11:27
 * @Version V1.0
 **/

@Component
public class Receiver {

    @Autowired
    private OrderMapper orderMapper;

    @RabbitHandler
    @RabbitListener(queues = "testMsg1")
    public void receiver(String msg) {
        System.out.println("Test1 receiver1: " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "testMsg2")
    public void receiver2(String msg) {
        System.out.println("Test2 receiver2:" + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "testObj")
    public void receiver3(Order order) {
        orderMapper.addOrder(order);
        System.out.println(order.toString());
    }

    /**
     * 只接一个topic
     * @param msg
     */
    @RabbitHandler
    @RabbitListener(queues = "topic.message")
    public void process1(String msg) {
        System.out.println("TopicReceiver1: " + msg);
    }

    /**
     * 接收多个topic
     * @param msg
     */
    @RabbitHandler
    @RabbitListener(queues = "topic.messages")
    public void process2(String msg) {
        System.out.println("TopicReceiver2: " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanoutQueue")
    public void fanoutReceive(String message){
        System.out.println(message);
    }

    @RabbitHandler
    @RabbitListener(queues = "headersQueue")
    public void headersReceive(byte[] message){
        System.out.println(new String (message));
    }
}