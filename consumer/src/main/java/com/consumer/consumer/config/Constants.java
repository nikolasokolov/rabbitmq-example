package com.consumer.consumer.config;

public class Constants {

    public static final String TOPIC_EXCHANGE = "topicExchange";

    public static final String SUCCESS_QUEUE = "successQueue";
    public static final String ERROR_QUEUE = "errorQueue";

    public static final String SUCCESS_ROUTING_KEY = "#.success";
    public static final String ERROR_ROUTING_KEY = "#.error";

    public static final String RABBIT_CONTAINER_FACTORY = "rabbitListenerContainerFactory";
}
