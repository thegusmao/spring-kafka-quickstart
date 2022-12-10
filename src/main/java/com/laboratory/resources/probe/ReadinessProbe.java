package com.laboratory.resources.probe;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/health/readiness")

public class ReadinessProbe {

    Logger logger = LoggerFactory.getLogger(ReadinessProbe.class);

    @GetMapping(produces = "application/json")
    ResponseEntity<String> readiness() {
        return ResponseEntity.ok().build();
    }
}
