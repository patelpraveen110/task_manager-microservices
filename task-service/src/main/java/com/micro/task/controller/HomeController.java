package com.micro.task.controller;

import com.micro.task.model.Task;
import com.micro.task.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/tasks")
    public ResponseEntity<String> getTaskById(){

        return new ResponseEntity<>("Welcome to task service", HttpStatus.OK);
    }
}
