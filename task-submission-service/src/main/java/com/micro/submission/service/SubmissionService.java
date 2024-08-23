package com.micro.submission.service;

import com.micro.submission.model.Submission;

import java.util.List;

public interface SubmissionService {

    Submission submitTask(Long taskId, String githubLink, Long userID) throws Exception;

    Submission getTaskSubmissionById(Long submissionId) throws Exception;

    List<Submission> getAllTaskSubmission();

    List<Submission> getTaskSubmissionByTaskId(Long taskId);

    Submission acceptOrDeclineSubmission(Long id, String status) throws Exception;
}
