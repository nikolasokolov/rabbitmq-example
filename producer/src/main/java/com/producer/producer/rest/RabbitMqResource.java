package com.producer.producer.rest;

import com.producer.producer.service.SenderInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static com.producer.producer.config.Constants.*;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class RabbitMqResource {

    private final SenderInterface senderInterface;

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public ResponseEntity sendSuccessMessage() {
        senderInterface.sendMessage(TOPIC_EXCHANGE, SUCCESS_ROUTING_KEY, "success message");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ResponseEntity sendErrorMessage() {
        senderInterface.sendMessage(TOPIC_EXCHANGE, ERROR_ROUTING_KEY, "error message");
        return ResponseEntity.ok().build();
    }
}
