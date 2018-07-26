package com.zxsoft.demo2.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    final static String queueRoutePattern1 = "topic.common.*";
    final static String getQueueRoutePattern2 = "topic.controller.#";

    @Bean
    public Queue queue1(){
        return new Queue("1");
    }
    @Bean
    public Queue queue2(){
        return new Queue("2");
    }
    @Bean
    public Queue queue3(){
        return new Queue("3");
    }

    @Bean
    TopicExchange exchange1(){
        return new TopicExchange("exchange1");
    }
    @Bean
    TopicExchange exchange2(){
        return new TopicExchange("exchange2");
    }
    @Bean
    Binding bindingExchange1(){
        return BindingBuilder.bind(queue1()).to(exchange1()).with(queueRoutePattern1);
    }
    @Bean
    Binding bindingExchange2(){
        return BindingBuilder.bind(queue2()).to(exchange2()).with(getQueueRoutePattern2);
    }
}
