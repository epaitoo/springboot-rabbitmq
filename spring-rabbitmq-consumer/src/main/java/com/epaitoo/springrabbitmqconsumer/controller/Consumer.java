package com.epaitoo.springrabbitmqconsumer.controller;

import com.epaitoo.springrabbitmqconsumer.configuration.DirectExchangeConfiguration;
import com.epaitoo.springrabbitmqconsumer.configuration.FanOutExchangeConfiguration;
import com.epaitoo.springrabbitmqconsumer.configuration.HeaderExchangeConfiguration;
import com.epaitoo.springrabbitmqconsumer.configuration.TopicExchangeConfiguration;
import com.epaitoo.springrabbitmqconsumer.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = DirectExchangeConfiguration.QUEUE_A)
    public void listener(Message message) {
        System.out.println("Direct Exchange Message " + message);
    }

    @RabbitListener(queues = FanOutExchangeConfiguration.QUEUE_B)
    public void fanOutExchangeListener(Message message) {
        System.out.println("Fanout Exchange Message " + message);
    }

    @RabbitListener(queues = TopicExchangeConfiguration.QUEUE_C)
    public void topicExchangeListener(Message message) {
        System.out.println("Topic Exchange Message " + message);
    }

    @RabbitListener(queues = HeaderExchangeConfiguration.QUEUE_H)
    public void headerExchangeListener(String message) {
        System.out.println("Header Exchange Message " + message);
    }



}
