package com.mspr_kawa.clients.main.rabbitmq;

import com.mspr_kawa.clients.main.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    private final RabbitMQConfig rabbitMQConfig;

    public RabbitMQJsonConsumer(RabbitMQConfig rabbitMQConfig) {
        this.rabbitMQConfig = rabbitMQConfig;
    }

    @RabbitListener(queues = "#{JsonQueue.name}")
    public void consumeJsonMessage(Customer customer) {
        LOGGER.info(String.format("Received JSON message -> %s", customer.toString()));
    }
}
