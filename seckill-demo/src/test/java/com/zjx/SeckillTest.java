package com.zjx;

import com.zjx.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/3/20 18:06
 * @Version V1.0
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeckillTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ThreadPoolTaskScheduler scheduler;

    /**
     * Redis实现分布式锁测试
     * @throws InterruptedException
     */
    @Test
    public void seckillRedisTest() throws InterruptedException {
        int num = 10000;
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(num);

        for (int i = 0; i < num; i++) {
            scheduler.execute(new Runnable() {
                @Override
                public void run() {
                    orderService.seckill();
                }
            });
            countDownLatch.countDown();
        }
        Thread.sleep(100L);
        countDownLatch.await();
    }

    /**
     * Redisson实现分布式锁测试
     * @throws InterruptedException
     */
    @Test
    public void seckillRedissonTest() throws InterruptedException {
        int num = 10000;
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(num);

        for (int i = 0; i < num; i++) {
            scheduler.execute(new Runnable() {
                @Override
                public void run() {
                    orderService.seckillRedisson();
                }
            });
            countDownLatch.countDown();
        }
        Thread.sleep(100L);
        countDownLatch.await();
    }
}
