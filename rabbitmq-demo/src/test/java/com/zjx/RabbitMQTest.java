package com.zjx;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/3/28 15:03
 * @Version V1.0
 **/
public class RabbitMQTest {

    /**
     * 生产者
     *
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    @Test
    public void testBasicPublish() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost("/");
        factory.setHost("127.0.0.1");
        factory.setPort(AMQP.PROTOCOL.PORT);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String EXCHANGE_NAME = "exchange.direct";
        String QUEUE_NAME = "queue_name";
        String ROUTING_KEY = "key";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        /*
         * Confirm机制
         * channel.confirmSelect();
         * channel.waitForConfirms();
         *
         * 事务机制
         * channel.txSelect();
         * channel.txCommit();
         * channel.txRollback();
         */
        channel.confirmSelect(); // Confirm机制确认消息
        String message = "Hello RabbitMQ:";
        for (int i = 0; i < 5; i++) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, (message + i).getBytes("UTF-8"));
        }

        channel.close();
        connection.close();
    }


    /**
     * 消费者
     *
     * @throws Exception
     */
    @Test
    public void testBasicConsumer() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost("/");
        factory.setHost("127.0.0.1");
        factory.setPort(AMQP.PROTOCOL.PORT);    // 5672
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        String EXCHANGE_NAME = "exchange.direct";
        String QUEUE_NAME = "queue_name";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "key");


//        GetResponse response = channel.basicGet(QUEUE_NAME, false);
//        byte[] body = response.getBody();
//        System.out.println(new String(body).toString());

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(message);

                if (message.contains(":3")) {
                    // requeue：重新入队列，false：直接丢弃，相当于告诉队列可以直接删除掉
                    // channel.basicReject(envelope.getDeliveryTag(), false);

                    channel.basicRecover(true); // 重新投递
                } else {
                    channel.basicAck(envelope.getDeliveryTag(), false); // 消息服务器删除消息
                }
            }
        };

        boolean autoAck = false; // 手动应答
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

        Thread.sleep(100000);
    }

}
