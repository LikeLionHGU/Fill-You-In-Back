package com.likelion.fillyouinback.member.dto;

import com.likelion.fillyouinback.member.controller.request.UpdateMyProfileRequest;
import com.likelion.fillyouinback.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MemberDto {
  private String email;
  private String authority;
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

  public static MemberDto from(Member member) {
    return MemberDto.builder()
        .email(member.getEmail())
        .authority(member.getAuthority().getKorean())
        .firstName(member.getFirstName())
        .lastName(member.getLastName())
        .semester(member.getSemester())
        .department(member.getDepartment())
        .affiliation(member.getAffiliation())
        .googleProfilePictureUrl(member.getGoogleProfilePictureUrl())
        .profileImage(member.getProfileImage())
        .bannerImage(member.getBannerImage())
        .fields(splitString(member.getFields()))
        .jobs(splitString(member.getJobs()))
        .skills(splitString(member.getSkills()))
        .introduction(member.getIntroduction())
        .build();
  }

  public static MemberDto from(UpdateMyProfileRequest request) {
    return MemberDto.builder()
        .semester(request.getSemester())
        .department(request.getDepartment())
        .affiliation(request.getAffiliation())
        .fields(request.getFields())
        .jobs(request.getJobs())
        .skills(request.getSkills())
        .introduction(request.getIntroduction())
        .build();
  }
  private static List<String> splitString(String str) {
    return str == null ? null : List.of(str.split(","));
  }
}
