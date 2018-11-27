package com.consumer.consumer.service;

import com.consumer.consumer.model.Status;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.consumer.consumer.config.Constants.*;

@Service
public class RabbitMqListener {

    @RabbitListener(queues = SUCCESS_QUEUE, containerFactory = RABBIT_CONTAINER_FACTORY)
    public void listenSuccessQueue(Status status, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicAck(tag, true);
        System.out.println("Success listener: " + status);
    }

    @RabbitListener(queues = ERROR_QUEUE, containerFactory = RABBIT_CONTAINER_FACTORY)
    public void listenErrorQueue(Status status, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicAck(tag, true);
        System.out.println("Error listener: " + status);
    }
}
