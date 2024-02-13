package com.likelion.fillyouinback.skill.dto;

import com.likelion.fillyouinback.skill.domain.Skill;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SkillDto {
  private String name;

  public static SkillDto from(Skill skill) {
    return SkillDto.builder().name(skill.getName()).build();
  }
}
