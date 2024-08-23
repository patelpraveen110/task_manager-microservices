package com.micro.submission.service;

import com.micro.submission.model.Submission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService{
    @Override
    public Submission submitTask(Long taskId, String githubLink, Long userID) throws Exception {
        return null;
    }

    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {
        return null;
    }

    @Override
    public List<Submission> getAllTaskSubmission() {
        return List.of();
    }

    @Override
    public List<Submission> getTaskSubmissionByTaskId(Long taskId) {
        return List.of();
    }

    @Override
    public Submission acceptOrDeclineSubmission(Long id, String status) throws Exception {
        return null;
    }
}
