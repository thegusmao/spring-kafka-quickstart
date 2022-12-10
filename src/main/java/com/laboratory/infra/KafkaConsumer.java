package com.laboratory.infra;

import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.laboratory.domain.Event;

@Service
public class KafkaConsumer {
    
    private Logger log = Logger.getLogger(KafkaConsumer.class.getSimpleName());
    
    @Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "springboot")
    public void consume(ConsumerRecord<String, Event> payload){
        log.info(String.format("Tópico: %s", topicName));
        log.info(String.format("key: %s", payload.key()));
        log.info(String.format("Headers: %s", payload.headers()));
        log.info(String.format("Partion: %s", payload.partition()));
        log.info(String.format("Order: %s", payload.value()));

    }
}
