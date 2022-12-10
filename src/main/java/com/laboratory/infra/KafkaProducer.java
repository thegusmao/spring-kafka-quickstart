package com.laboratory.infra;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.laboratory.domain.Event;

@Service
public class KafkaProducer {
    
    private Logger log = Logger.getLogger(KafkaConsumer.class.getSimpleName());
    
    @Value("${topic.name.producer}")
    private String topicName;
    
    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    public void send(Event message){
        log.info(String.format("Payload enviado: %s", message));
        kafkaTemplate.send(topicName, message);
    }
}
