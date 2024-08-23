package com.micro.task.controller;

import com.micro.task.model.Task;
import com.micro.task.model.UserDto;
import com.micro.task.service.TaskService;
import com.micro.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(
            @RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto userDto = userService.getUserProfile(jwt);
        Task createdTask = taskService.createTask(task, userDto.getRole());
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId,
                                            @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto userDto = userService.getUserProfile(jwt);
        Task task = taskService.getTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }
}
