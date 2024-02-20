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
public class MemberProfileResponse {
  private Long id;
  private String email;
  private String firstName;
  private String lastName;
  private Integer semester;
  private String department;
  private List<Affiliation> affiliations;
  private String profileImageUrl;
  private String introduction;
  private Boolean isScrapped;
  private List<Skill> skills;
  private List<Job> jobs;
  private List<Field> fields;

  @Getter
  @Builder
  private static class Affiliation {
    private String name;

    private static Affiliation from(String name) {
      return Affiliation.builder().name(name).build();
    }
  }

  @Getter
  @Builder
  private static class Skill {
    private String name;

    private static Skill from(MemberSkillDto skill) {
      return Skill.builder().name(skill.getName()).build();
    }
  }

  @Getter
  @Builder
  private static class Job {
    private String name;

    private static Job from(MemberJobDto job) {
      return Job.builder().name(job.getName()).build();
    }
  }

  @Getter
  @Builder
  private static class Field {
    private String name;

    private static Field from(MemberFieldDto field) {
      return Field.builder().name(field.getName()).build();
    }
  }

  public static MemberProfileResponse from(MemberDto dto) {
    return MemberProfileResponse.builder()
        .id(dto.getId())
        .email(dto.getEmail())
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .semester(dto.getSemester())
        .department(dto.getDepartment())
        .affiliations(dto.getAffiliations().stream().map(Affiliation::from).toList())
        .profileImageUrl(dto.getProfileImageUrl())
        .introduction(dto.getIntroduction())
        .isScrapped(dto.getIsScrapped())
        .skills(dto.getSkills().stream().map(Skill::from).toList())
        .jobs(dto.getJobs().stream().map(Job::from).toList())
        .fields(dto.getFields().stream().map(Field::from).toList())
        .build();
  }
}
