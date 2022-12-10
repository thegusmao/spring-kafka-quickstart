package com.laboratory.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.laboratory.domain.Event;
import com.laboratory.infra.KafkaProducer;

@RestController
@RequestMapping("/api/events")
public class KafkaResource {

    @Autowired
    KafkaProducer producer;

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> sendMessage(@RequestBody Event message){
        producer.send(message);
        return ResponseEntity.ok().build();
    }
}
