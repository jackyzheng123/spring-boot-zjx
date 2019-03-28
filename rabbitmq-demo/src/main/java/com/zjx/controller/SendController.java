package com.zjx.controller;

import com.zjx.entity.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 发送者
 * @Author Carson Cheng
 * @Date 2019/3/27 11:10
 * @Version V1.0
 **/

@RestController
public class SendController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/send")
    public String send() {
        String content = "Current Date: " + new Date();
        amqpTemplate.convertAndSend("testMsg1", content);
        return content;
    }

    /**
     * 发送者循环发送10个消息
     *
     * @return
     */
    @RequestMapping("/multiSend")
    public String multiSend() {
        StringBuilder times = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            long time = System.nanoTime();
            amqpTemplate.convertAndSend("testMsg1", "第" + i + "次发送的时间：" + time);
            times.append(time + "<br>");
        }
        return times.toString();
    }

    /**
     * 多对多发送消息
     *
     * @return
     */
    @RequestMapping("/multi2MultiSend")
    public String mutil2MutilSend() {
        StringBuilder times = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            long time = System.nanoTime();
            amqpTemplate.convertAndSend("testMsg1", "第" + i + "次发送的时间：" + time);
            amqpTemplate.convertAndSend("testMsg2", "第" + i + "次发送的时间：" + time);
            times.append(time + "<br>");
        }
        return times.toString();
    }

    /**
     * 发送实体对象, 该对象要实现序列化接口
     */
    @GetMapping("/sendObject")
    public String sendObject() {
        Order order = new Order("zjx", "iPhone XS", new BigDecimal(7999.00), 2);
        amqpTemplate.convertAndSend("testObj", order);

        return "send success";
    }


    @RequestMapping("/topicSend1")
    public String topicSend1() {
        String context = "my topic 1";
        System.out.println("发送者说 : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.message", context);
        return context;
    }

    @RequestMapping("/topicSend2")
    public String topicSend2() {
        String context = "my topic 2";
        System.out.println("发送者说 : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.messages", context);
        return context;
    }

    @GetMapping("/fanout")
    public String fanout(Object message) {
        String context = "fanout msg";
        amqpTemplate.convertAndSend("fanoutExchange", "fanoutQueue", context);
        return context;
    }

    @GetMapping("/headers")
    public String headers(Object message) {
        String msg = "My headers queue";
        MessageProperties properties = new MessageProperties();
        properties.setHeader("header1", "value1");
        properties.setHeader("header2", "value2");
        Message obj = new Message(msg.getBytes(), properties);
        amqpTemplate.convertAndSend("headersExchange", "headersQueue", obj);
        return msg;
    }


}
