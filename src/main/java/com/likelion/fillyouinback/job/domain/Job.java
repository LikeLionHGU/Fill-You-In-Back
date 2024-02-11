package com.likelion.fillyouinback.job.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import jakarta.persistence.*;

@Entity
public class Job extends BaseTime {
    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column(name = "name", length = 60, nullable = false)
    private String name;
}
