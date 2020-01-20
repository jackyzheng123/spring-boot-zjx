package com.sleb.springcloud.rabbitmqproducerdemo.service;

import com.sleb.springcloud.rabbitmqproducerdemo.domain.PolicyModal;

/**
 * @description:
 * @author: lazasha
 * @date: 2019/12/23  16:46
 **/

public interface AcceptProducerService {

    void sendMessage(PolicyModal policyModal);

    void sendDlxMsg(PolicyModal policyModal);

    void sendDelayMsg(PolicyModal policyModal);
}
