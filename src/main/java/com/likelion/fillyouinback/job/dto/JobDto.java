package com.likelion.fillyouinback.job.dto;

import com.likelion.fillyouinback.job.domain.Job;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JobDto {
  private String name;

  public static JobDto from(Job job) {
    return JobDto.builder().name(job.getName()).build();
  }
}
