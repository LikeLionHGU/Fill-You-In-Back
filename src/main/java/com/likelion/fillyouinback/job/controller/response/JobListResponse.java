package com.likelion.fillyouinback.job.controller.response;

import com.likelion.fillyouinback.job.dto.JobDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JobListResponse {
  private final List<Job> jobs;

  public JobListResponse(List<JobDto> dtos) {
    this.jobs = dtos.stream().map(Job::from).toList();
  }

  @Builder
  @Getter
  private static class Job {
    private String name;

    private static Job from(JobDto dto) {
      return Job.builder().name(dto.getName()).build();
    }
  }
}
