package com.zxsoft.demo2.config;

import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void Send(){
        String context = "hello " + DateTime.now().toString();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topic.common.a","sender1");
    }
}
