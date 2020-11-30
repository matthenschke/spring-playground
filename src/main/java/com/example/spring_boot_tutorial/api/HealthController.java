package com.example.spring_boot_tutorial.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthController {
    @GetMapping(path = "/api/v1/health")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }
}
