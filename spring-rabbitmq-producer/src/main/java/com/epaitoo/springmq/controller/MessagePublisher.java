package com.epaitoo.springmq.controller;


import com.epaitoo.springmq.configuration.DirectExchangeConfiguration;
import com.epaitoo.springmq.configuration.TopicExchangeConfiguration;
import com.epaitoo.springmq.model.Message;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    @Autowired
    private FanoutExchange fanoutExchange;

    @Autowired
    private TopicExchange topicExchange;

    @Autowired
    private HeadersExchange headersExchange;

    @PostMapping("/direct-exchange-publish")
    public String publishDirectMessage(@RequestBody Message message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        rabbitTemplate.convertAndSend(directExchange.getName(), DirectExchangeConfiguration.ROUTING_A, message);
        return "Direct Exchange Message Published";

    }

    @PostMapping("/fanout-exchange-publish")
    public String publishFanOutMessage(@RequestBody Message message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
        return "Fanout Exchange Message Published";

    }

    @PostMapping("/topic-exchange-publish")
    public String publishTopicMessage(@RequestBody Message message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        rabbitTemplate.convertAndSend(topicExchange.getName(), TopicExchangeConfiguration.ROUTING_C, message);
        return "Topic Exchange Message Published";

    }

    @PostMapping("/header-exchange-publish/{message}")
    public String publishHeaderMessage(@PathVariable(value = "message") String message) {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("tech", message);
        MessageConverter messageConverter = new SimpleMessageConverter();
        org.springframework.amqp.core.Message headerMessage = messageConverter.toMessage(message, messageProperties);
        rabbitTemplate.send(headersExchange.getName(), "", headerMessage);

        return "Header Exchange Message Published";

    }

    // Default Exchange
    @PostMapping("/default-exchange-publish")
    public String publishDefaultMessage(@RequestBody Message message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        rabbitTemplate.convertAndSend(TopicExchangeConfiguration.QUEUE_C, message);
        return "Default Exchange Message Published";

    }
}
