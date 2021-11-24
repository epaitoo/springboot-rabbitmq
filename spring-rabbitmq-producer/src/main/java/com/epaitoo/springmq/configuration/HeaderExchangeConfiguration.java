package com.epaitoo.springmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderExchangeConfiguration {

    public static final String QUEUE_H = "queue.H";

    @Bean
    public Queue queueH() {
        return new Queue(QUEUE_H, false);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("exchange.header");
    }

    @Bean
    public Binding bindingH(Queue queueH, HeadersExchange headersExchange){
        return BindingBuilder.bind(queueH)
                .to(headersExchange)
                .where("tech")
                .matches("java");
    }

    @Bean
    public MessageConverter messageConverterH() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplateH(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverterH());
        return rabbitTemplate;
    }



}
