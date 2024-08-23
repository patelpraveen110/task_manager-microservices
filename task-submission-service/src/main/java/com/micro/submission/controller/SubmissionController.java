package com.micro.submission.controller;

import com.micro.submission.model.Submission;
import com.micro.submission.model.UserDto;
import com.micro.submission.service.SubmissionService;
import com.micro.submission.service.TaskService;
import com.micro.submission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Submission> submitTask(
            @RequestParam Long taskId,
            @RequestParam String githubLink,
            @RequestHeader("Authorization") String jwt) throws Exception{

        UserDto userDto = userService.getUserProfile(jwt);
        Submission submission = submissionService.submitTask(taskId, githubLink,userDto.getUserId(), jwt);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception{

        UserDto userDto = userService.getUserProfile(jwt);
        Submission submission = submissionService.getTaskSubmissionById(id);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Submission>> getAllSubmissions(
            @RequestHeader("Authorization") String jwt) throws Exception{

        UserDto userDto = userService.getUserProfile(jwt);
        List<Submission> submissions = submissionService.getAllTaskSubmission();
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getAllSubmissions(
            @PathVariable Long taskId,
            @RequestHeader("Authorization") String jwt) throws Exception{

        UserDto userDto = userService.getUserProfile(jwt);
        List<Submission> submissions = submissionService.getTaskSubmissionByTaskId(taskId);
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> acceptOrDecline(
            @PathVariable Long taskId,
            @RequestParam("status") String status,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);
        Submission submission = submissionService.acceptOrDeclineSubmission(taskId,status);
        return new ResponseEntity<>(submission, HttpStatus.OK);

    }
}
