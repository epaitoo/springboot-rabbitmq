package com.epaitoo.springrabbitmqconsumer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanOutExchangeConfiguration {

    public static final String ROUTING_B = "routing.B";
    public static final String QUEUE_B = "queue.B";

    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B, false);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    public Binding bindingB(Queue queueB, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueB)
                .to(fanoutExchange);
    }

    @Bean
    public MessageConverter messageConverterB() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplateB(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverterB());
        return rabbitTemplate;
    }
}
