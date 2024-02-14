package com.likelion.fillyouinback.affiliation.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Affiliation extends BaseTime {
  @Id
  @Column(name = "affiliation_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long affiliationId;

  @Column(name = "name", length = 60, nullable = false)
  private String name;
}
