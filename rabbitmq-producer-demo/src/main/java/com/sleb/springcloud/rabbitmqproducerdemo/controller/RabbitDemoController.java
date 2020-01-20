package com.sleb.springcloud.rabbitmqproducerdemo.controller;

import com.sleb.springcloud.rabbitmqproducerdemo.domain.PolicyModal;
import com.sleb.springcloud.rabbitmqproducerdemo.service.AcceptProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitDemoController {
    private final AcceptProducerService acceptProducerService;

    public RabbitDemoController(AcceptProducerService acceptProducerService) {
        this.acceptProducerService = acceptProducerService;
    }

    @GetMapping("/sendmult")
    public String sendMultiple() {
        try {
            for (int i = 0; i < 90; i++) {
                acceptProducerService.sendMessage(new PolicyModal(String.valueOf(i), "laza" + i, i, i, System.currentTimeMillis()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "send end";
    }

    @GetMapping("/sendsing")
    public String sendSingle() {
        try {
            acceptProducerService.sendMessage(new PolicyModal(String.valueOf(91), "laza" + 91, 91, 91, System.currentTimeMillis()));

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "send end";
    }

    @GetMapping("/sendDlx")
    public String sendDlx() {
        try {
            acceptProducerService.sendDlxMsg(new PolicyModal(String.valueOf(92), "laza" + 92, 92, 92, System.currentTimeMillis()));

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "send end";
    }

    @GetMapping("/sendDelay")
    public String sendDelay() {
        try {
            acceptProducerService.sendDelayMsg(new PolicyModal(String.valueOf(93), "laza" + 93, 93, 93, System.currentTimeMillis()));

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "send end";
    }
}
