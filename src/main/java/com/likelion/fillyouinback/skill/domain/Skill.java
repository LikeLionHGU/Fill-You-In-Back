package com.likelion.fillyouinback.skill.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
public class Skill extends BaseTime {
  @Id
  @Column(name = "skill_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long skillId;

  @Column(name = "name", length = 60, nullable = false)
  private String name;
}
