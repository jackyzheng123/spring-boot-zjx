package com.zjx.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2020/3/5 11:44
 * @Version V1.0
 **/
@Component
@Slf4j
public class SimpleReceiver {

    @KafkaListener(topics = {"topic1", "topic2"})
    public void listen(String data) {
        log.info("收到消息：" + data);
    }
}