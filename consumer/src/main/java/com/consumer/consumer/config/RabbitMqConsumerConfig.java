package com.consumer.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.consumer.consumer.config.Constants.*;

@Configuration
public class RabbitMqConsumerConfig {

    @Bean(name = TOPIC_EXCHANGE)
    public TopicExchange createTopicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE, true, false, null);
    }

    @Bean(name = SUCCESS_QUEUE)
    public Queue createSuccessQueue() {
        return new Queue(SUCCESS_QUEUE, true, false, false);
    }

    @Bean(name = ERROR_QUEUE)
    public Queue createErrorQueue() {
        return new Queue(ERROR_QUEUE, true, false, false);
    }

    @Bean
    public Binding createSuccessQueueBinding(@Qualifier(TOPIC_EXCHANGE) TopicExchange topicExchange,
                                             @Qualifier(SUCCESS_QUEUE) Queue queue) {
        return BindingBuilder.bind(queue).to(topicExchange).with(SUCCESS_ROUTING_KEY);
    }

    @Bean
    public Binding createErrorQueueBinding(@Qualifier(TOPIC_EXCHANGE) TopicExchange topicExchange,
                                           @Qualifier(ERROR_QUEUE) Queue queue) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ERROR_ROUTING_KEY);
    }

    @Bean(name = RABBIT_CONTAINER_FACTORY)
    public SimpleRabbitListenerContainerFactory createContainerFactory(ConnectionFactory connectionFactory,
                                                                       SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}
