package com.likelion.fillyouinback.member.controller.response;

import com.likelion.fillyouinback.member.dto.MemberDto;
import com.likelion.fillyouinback.memberField.dto.MemberFieldDto;
import com.likelion.fillyouinback.memberJob.dto.MemberJobDto;
import com.likelion.fillyouinback.memberSkill.dto.MemberSkillDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MyProfileResponse {
  private String email;
  private String firstName;
  private String lastName;
  private Integer semester;
  private String department;
  private List<String> affiliations;
  private String profileImageUrl;
  private List<Field> fields;
  private List<Job> jobs;
  private List<Skill> skills;
  private String introduction;
  private Boolean isFirstProfileVisit;

  @Getter
  @Builder
  private static class Field {
    private String name;
    private Boolean isPinned;

    protected static Field from(MemberFieldDto field) {
      return Field.builder().name(field.getName()).isPinned(field.getIsPinned()).build();
    }
  }

  @Getter
  @Builder
  private static class Job {
    private String name;
    private Boolean isPinned;

    protected static Job from(MemberJobDto job) {
      return Job.builder().name(job.getName()).isPinned(job.getIsPinned()).build();
    }
  }

  @Getter
  @Builder
  private static class Skill {
    private String name;
    private Boolean isPinned;

    protected static Skill from(MemberSkillDto skill) {
      return Skill.builder().name(skill.getName()).isPinned(skill.getIsPinned()).build();
    }
  }

  public static MyProfileResponse from(MemberDto dto) {
    return MyProfileResponse.builder()
        .email(dto.getEmail())
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .semester(dto.getSemester())
        .department(dto.getDepartment())
        .affiliations(dto.getAffiliations())
        .profileImageUrl(dto.getProfileImageUrl())
        .fields(dto.getFields().stream().map(Field::from).toList())
        .jobs(dto.getJobs().stream().map(Job::from).toList())
        .skills(dto.getSkills().stream().map(Skill::from).toList())
        .introduction(dto.getIntroduction())
        .isFirstProfileVisit(dto.getIsFirstProfileVisit())
        .build();
  }
}
