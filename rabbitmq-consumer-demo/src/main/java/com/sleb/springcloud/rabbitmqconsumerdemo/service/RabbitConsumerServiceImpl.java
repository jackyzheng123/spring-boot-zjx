package com.sleb.springcloud.rabbitmqconsumerdemo.service;

import com.rabbitmq.client.Channel;
import com.sleb.springcloud.rabbitmqconsumerdemo.util.DateUtils;
import com.sleb.springcloud.rabbitmqconsumerdemo.util.JsonUtils;
import com.sleb.springcloud.rabbitmqproducerdemo.domain.PolicyModal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.sleb.springcloud.rabbitmqconsumerdemo.service.RabbitMQConfigInfo.*;

/**
 * @description: rabbit mq消费者
 * @author: lazasha
 * @date: 2019/12/24  10:20
 **/

@Service
public class RabbitConsumerServiceImpl implements RabbitConsumerService {

    private final Logger logger = LoggerFactory.getLogger(RabbitConsumerServiceImpl.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitConsumerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = QUEUE_SLEB, durable = "true"),
            exchange = @Exchange(name = EXCHANGE_SLEB,
                    ignoreDeclarationExceptions = "true"),
            key = {ROUTING_KEY_SLEB}
    ))
    @Override
    public void process(Channel channel, Message message) throws IOException {
        String msg = new String(message.getBody());

        //PolicyModal类的全限名称（包名+类名）会带入到mq, 所以消费者服务一边必须有同样全限名称的类，否则接收会失败。
        PolicyModal policyModal = JsonUtils.JSON2Object(msg, PolicyModal.class);
        String policyNo = policyModal.getPolicyNo();

        Long time = System.currentTimeMillis();
        logger.info("接收时间：" + time + ";  发送时间:" + policyModal.getSendtime() + ";  消耗时间：" + (time - policyModal.getSendtime()));
        assert policyModal != null;
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了
            // 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            logger.info("接收处理成功： 接收信息时间: {}; 保单号: {}; 消息：{}", DateUtils.localDateTimeToString(LocalDateTime.now()), policyModal.getPolicyNo(), msg);

            throw new IOException("myself");
        } catch (IOException e) {
            logger.info("接收处理失败： 接收信息时间: {}; 保单号: {}; 消息：{}", DateUtils.localDateTimeToString(LocalDateTime.now()), policyModal.getPolicyNo(), msg);

            //丢弃这条消息,则不会重新发送了
            //一般不丢弃，超时后mq自动会转到死信队列（如果设置了超时时间和死信交换机和队列后）
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

            //将消息转到死信队列/延时队列进行处理
            //sendDelayQueue(msg, policyNo, RabbitMQConfigInfo.EXCHANGE_SLEB_DELAY, RabbitMQConfigInfo.ROUTING_KEY_SLEB_DELAY);
            sendDelayQueue(msg, policyNo, RabbitMQConfigInfo.EXCHANGE_SLEB_DLX, RabbitMQConfigInfo.ROUTING_KEY_SLEB_DLX);

        }
    }

    /**
     * 将消息转到延时队列进行处理
     *
     * @param msg
     * @param policyNo
     */
    public void sendDelayQueue(String msg, String policyNo, String exchange, String routingKey) {

        Message message = MessageBuilder.withBody(msg.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8")
                .setMessageId(policyNo).build();
        // 自定义消息唯一标识
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(message.getMessageProperties().getMessageId());
        rabbitTemplate.convertSendAndReceive(exchange, routingKey, message, correlationData);

    }

    /**
     * 监听死信队列
     *
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = RabbitMQConfigInfo.QUEUE_SLEB_DLX)
    public void receiveDlx(Message message, Channel channel) throws IOException {

        String msg = new String(message.getBody());
        logger.info("收到死信消息：{}", msg);

        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            logger.error("消息消费发生异常，error msg:{}", e.getMessage(), e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }

    }

    /**
     * 监听延时队列
     *
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = RabbitMQConfigInfo.QUEUE_SLEB_DELAY)
    public void receiveDelay(Message message, Channel channel) throws IOException {

        String msg = new String(message.getBody());
        logger.info("收到延时消息：{}", msg);

        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            logger.error("消息消费发生异常，error msg:{}", e.getMessage(), e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }

    }

}
