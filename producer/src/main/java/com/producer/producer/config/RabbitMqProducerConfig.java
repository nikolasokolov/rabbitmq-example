package com.producer.producer.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqProducerConfig {

    @Bean(name = "connectionFactory")
    public ConnectionFactory createConnectionFactory() {
        return new CachingConnectionFactory();
    }

    @Bean
    public RabbitTemplate createRabbitTemplate(@Qualifier("connectionFactory") ConnectionFactory connectionFactory,
                                               MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter createConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
