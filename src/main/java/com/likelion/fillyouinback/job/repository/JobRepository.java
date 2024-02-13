package com.likelion.fillyouinback.job.repository;

import com.likelion.fillyouinback.job.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {}
