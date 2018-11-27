package com.producer.producer.service;

public interface SenderInterface {

    void sendMessage(String exchange, String routingKey, String message);
}
