package com.micro.submission.service;

import com.micro.submission.model.Submission;
import com.micro.submission.model.TaskDto;
import com.micro.submission.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private TaskService taskService;

    @Override
    public Submission submitTask(Long taskId, String githubLink, Long userID, String jwt) throws Exception {

        TaskDto taskDto = taskService.getTaskById(taskId, jwt);
        if(taskDto!=null){
             Submission submission = new Submission();
             submission.setTaskId(taskId);
             submission.setUserId(userID);
             submission.setGithubLink(githubLink);
             submission.setSubmissionTime(LocalDateTime.now());
             return submissionRepository.save(submission);
        }
        throw new Exception("Task not found with id :" +taskId);
    }

    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {
         return submissionRepository.findById(submissionId).orElseThrow(()->new
                 Exception("Task submission not found with id"+ submissionId));
    }

    @Override
    public List<Submission> getAllTaskSubmission() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getTaskSubmissionByTaskId(Long taskId) {
        return submissionRepository.findByTaskId(taskId);
    }

    @Override
    public Submission acceptOrDeclineSubmission(Long id, String status) throws Exception {
        Submission submission = getTaskSubmissionById(id);
        submission.setStatus(status);
        if(status.equals("ACCEPT")){
            taskService.completeTask(submission.getTaskId());
        }
        return submissionRepository.save(submission);
    }
}
