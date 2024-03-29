package com.likelion.fillyouinback.field.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Field extends BaseTime {
  @Id
  @Column(name = "field_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long fieldId;

  @Column(name = "name", length = 60, nullable = false)
  private String name;
}
