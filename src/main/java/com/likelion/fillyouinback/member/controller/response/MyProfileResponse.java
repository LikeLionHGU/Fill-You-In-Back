package com.likelion.fillyouinback.member.controller.response;

import com.likelion.fillyouinback.member.dto.MemberDto;
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
  private String affiliation;
  private String googleProfilePictureUrl;
  private String profileImage;
  private String bannerImage;
  private List<String> fields;
  private List<String> jobs;
  private List<String> skills;
  private String introduction;

  public static MyProfileResponse from(MemberDto dto) {
    return MyProfileResponse.builder()
        .email(dto.getEmail())
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .semester(dto.getSemester())
        .department(dto.getDepartment())
        .affiliation(dto.getAffiliation())
        .googleProfilePictureUrl(dto.getGoogleProfilePictureUrl())
        .profileImage(dto.getProfileImage())
        .bannerImage(dto.getBannerImage())
        .fields(dto.getFields())
        .jobs(dto.getJobs())
        .skills(dto.getSkills())
        .introduction(dto.getIntroduction())
        .build();
  }
}
