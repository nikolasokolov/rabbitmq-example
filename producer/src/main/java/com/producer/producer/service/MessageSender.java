package com.producer.producer.service;

import com.producer.producer.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MessageSender implements SenderInterface {

    private final RabbitTemplate rabbitTemplate;

    private int counter = 0;

    @Override
    public void sendMessage(String exchange, String routingKey, String message) {
        Status status = new Status(counter, message);
        counter++;
        rabbitTemplate.convertAndSend(exchange, routingKey, status);
        System.out.println("Message sent: " + status);
    }
}
