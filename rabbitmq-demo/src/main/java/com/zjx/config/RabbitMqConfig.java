package com.zjx.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Direct模式(默认Exchange)
 *
 * 所有发送到Direct Exchange的消息被转发到RouteKey中指定的Queue。
 *
 * @Author Carson Cheng
 * @Date 2019/3/27 11:07
 * @Version V1.0
 **/
@Configuration
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        return jackson2JsonMessageConverter;
    }

    @Bean
    public Queue queue1() {
        return new Queue("testMsg1");
    }

    /**
     * 多对多的使用配置
     */
    @Bean
    public Queue queue2() {
        return new Queue("testMsg2");
    }

    /**
     * 发送对象
     *
     * @return
     */
    @Bean
    public Queue queue3() {
        return new Queue("testObj");
    }


}
