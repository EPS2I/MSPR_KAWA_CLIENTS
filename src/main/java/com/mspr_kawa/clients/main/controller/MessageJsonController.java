package com.mspr_kawa.clients.main.controller;

import com.mspr_kawa.clients.main.model.Customer;
import com.mspr_kawa.clients.main.rabbitmq.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageJsonController {
    private final RabbitMQJsonProducer jsonProducer;
    public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish/json")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Customer customer){
        jsonProducer.sendJsonMessage(customer);
        return ResponseEntity.ok(" JSon Message sent to RabbitMQ ....");
    }
}
