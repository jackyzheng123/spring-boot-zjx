package com.sleb.springcloud.rabbitmqconsumerdemo.service;

/**
 * @description:
 * @author: lazasha
 * @date: 2019/12/24  13:10
 **/

public class RabbitMQConfigInfo {

    /**
     * 投保消息交换机的名字
     */
    public static final String EXCHANGE_SLEB = "exchange_sleb";

    /**
     * 投保消息队列
     */
    public static final String QUEUE_SLEB = "queue_sleb";

    /**
     * 投保消息路由键
     */
    public static final String ROUTING_KEY_SLEB = "routing_key_sleb";


    /**
     *  投保消息死信交换机
     */
    public static final String EXCHANGE_SLEB_DLX = "exchange_sleb_dlx";
    /**
     * 投保消息死信队列
     */
    public static final String QUEUE_SLEB_DLX = "queue_sleb_dlx";

    /**
     * 投保死信消息路由键
     */
    public static final String ROUTING_KEY_SLEB_DLX = "routing_key_sleb_dlx";


    /**
     * 投保消息延迟交换机
     */
    public static final String EXCHANGE_SLEB_DELAY = "exchange_sleb_delay";
    /**
     * 投保消息延迟队列
     */
    public static final String QUEUE_SLEB_DELAY = "queue_sleb_delay";

    /**
     * 投保延迟消息路由键
     */
    public static final String ROUTING_KEY_SLEB_DELAY = "routing_key_sleb_delay";
}
