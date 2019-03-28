package com.zjx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description Headers模式, 通过添加属性 key-value匹配
 * @Author Carson Cheng
 * @Date 2019/3/28 10:49
 * @Version V1.0
 **/

@Configuration
public class RabbitMqHeadersConfig {

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headersExchange");
    }

    @Bean
    public Queue headersQueue() {
        return new Queue("headersQueue", true);
    }

    @Bean
    public Binding headersBinding() {
        Map<String,Object> map  = new HashMap<String, Object>();
        map.put("header1","value1");
        map.put("header2","value2");
        //满足key-value才会往队列中放东西
        return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
    }

}
