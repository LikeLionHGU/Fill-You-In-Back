package com.likelion.fillyouinback.department.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Department extends BaseTime {
  @Id
  @Column(name = "department_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long departmentId;

  @Column(name = "name", length = 60, nullable = false)
  private String name;
}
