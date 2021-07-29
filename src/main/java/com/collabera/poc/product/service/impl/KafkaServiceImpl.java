package com.collabera.poc.product.service.impl;

import com.collabera.poc.product.service.KafkaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class KafkaServiceImpl implements KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    @Autowired
    public KafkaServiceImpl(
        final KafkaTemplate<String, String> kafkaTemplate,
        @Value("${kafka.topic}") final String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    /**
     * Send Message
     * @param message
     */
    @Override
    public void sendMessage(final String message) {
        log.info("Sending message...");
        kafkaTemplate.send(topic, message);
        log.info("Message successfully sent.");
    }
}
