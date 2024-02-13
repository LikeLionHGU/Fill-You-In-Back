package com.likelion.fillyouinback.skill.controller.response;

import com.likelion.fillyouinback.skill.dto.SkillDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SkillListResponse {
  private final List<Skill> skills;

  public SkillListResponse(List<SkillDto> dtos) {
    this.skills = dtos.stream().map(Skill::from).toList();
  }

  @Builder
  @Getter
  private static class Skill {
    private String name;

    public static Skill from(SkillDto dto) {
      return Skill.builder().name(dto.getName()).build();
    }
  }
}
