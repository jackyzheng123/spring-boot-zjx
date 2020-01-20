package com.sleb.springcloud.rabbitmqproducerdemo.service;

import com.sleb.springcloud.rabbitmqproducerdemo.domain.PolicyDataEx;
import com.sleb.springcloud.rabbitmqproducerdemo.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @description: 如果消息没有到exchange, 则confirm回调, ack=false
 * 如果消息到达exchange,则confirm回调,ack=true
 * @author: lazasha
 * @date: 2019/12/24  13:28
 **/
@Service
public class AcceptConfirmCallback implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(AcceptConfirmCallback.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        PolicyDataEx dataEx = (PolicyDataEx) correlationData;
        assert dataEx != null;
        String policyNo = dataEx.getId();

        if (ack) {
            logger.info("消息发送成功, 返回时间: {}, 保单号: {}, 发送消息: {}" + DateUtils.localDateTimeToString(LocalDateTime.now()),
                    policyNo, dataEx.getMessage());

        } else {
            logger.info("消息发送失败,未路由到交换机, 失败原因: {}, 返回时间: {}, 发送消息: {}, 保单号: {}", cause
                    , DateUtils.localDateTimeToString(LocalDateTime.now()), dataEx.getMessage(), policyNo);
            //更新数据库，让批处理来处理为发送成功的消息
            //发送邮件，提升该保单发送mq不成功
        }
    }
}
