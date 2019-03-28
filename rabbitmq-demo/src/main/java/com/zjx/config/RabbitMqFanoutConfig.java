package com.zjx.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Fanout模式（广播） 将消息分发到所有的绑定队列，无routingkey的概念
 *
 * 所有发送到Fanout Exchange的消息都会被转发到与该Exchange 绑定(Binding)的所有Queue上。
 *
 * @Author Carson Cheng
 * @Date 2019/3/28 10:49
 * @Version V1.0
 **/

@Configuration
public class RabbitMqFanoutConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding fanoutBinding(){
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue("fanoutQueue");
    }

}
