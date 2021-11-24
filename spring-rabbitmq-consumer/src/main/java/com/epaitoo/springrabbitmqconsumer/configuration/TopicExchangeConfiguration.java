package com.epaitoo.springrabbitmqconsumer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfiguration {

    public static final String ROUTING_C = "routing.C";
    public static final String QUEUE_C = "queue.C";

    @Bean
    public Queue queueC() {
        return new Queue(QUEUE_C, false);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("exchange.topic");
    }

    @Bean
    public Binding bindingC(Queue queueC, TopicExchange topicExchange){
        return BindingBuilder.bind(queueC)
                .to(topicExchange)
                .with(ROUTING_C);
    }

    @Bean
    public MessageConverter messageConverterC() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplateC(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverterC());
        return rabbitTemplate;
    }




}
