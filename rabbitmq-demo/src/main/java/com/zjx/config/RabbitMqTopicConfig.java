package com.zjx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Topic模式多关键字匹配 ,Topic Exchange是RabbitMQ中最灵活的一种方式，它能够根据routing_key自由的绑定不同的队列
 *
 * TopicExchange 将RouteKey 和某Topic 进行模糊匹配。此时队列需要绑定一个Topic。
 * 可以使用通配符进行模糊匹配，符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。
 * 因此“log.#”能够匹配到“log.info.oa”，但是“log.*” 只会匹配到“log.error”
 *
 * @Author Carson Cheng
 * @Date 2019/3/27 12:47
 * @Version V1.0
 **/

@Configuration
public class RabbitMqTopicConfig {

    //只接一个topic
    final static String message = "topic.message";
    //接收多个topic
    final static String messages = "topic.messages";

    @Bean
    public Queue queueMessage() {
        return new Queue(RabbitMqTopicConfig.message);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(RabbitMqTopicConfig.messages);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    /**
     * Topic Exchange 转发消息主要是根据通配符，队列topic.message只能匹配topic.message的路由。
     * 而topic.messages匹配路由规则是topic.#，所以它可以匹配topic.开头的全部路由。而topic.#发送的消息也只能是topic.#的接受者才能接收。
     */
    @Bean
    public Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    public Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        //这里的#表示零个或多个词。
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
