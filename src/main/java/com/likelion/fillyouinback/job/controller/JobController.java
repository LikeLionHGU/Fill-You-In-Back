package com.likelion.fillyouinback.job.controller;

import com.likelion.fillyouinback.job.controller.response.JobListResponse;
import com.likelion.fillyouinback.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fillyouin/jobs")
public class JobController {
    private final JobService jobService;

    @GetMapping
    public ResponseEntity<JobListResponse> getJobList() {
        return ResponseEntity.ok(new JobListResponse(jobService.getJobList()));
    }
}
