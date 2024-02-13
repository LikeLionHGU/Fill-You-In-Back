package com.likelion.fillyouinback.job.service;

import com.likelion.fillyouinback.job.dto.JobDto;
import com.likelion.fillyouinback.job.repository.JobRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {
  private final JobRepository jobRepository;

  public List<JobDto> getJobList() {
    return jobRepository.findAll().stream().map(JobDto::from).toList();
  }
}
