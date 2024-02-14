package com.likelion.fillyouinback.member.dto;

import com.likelion.fillyouinback.member.controller.request.UpdateMyProfileRequest;
import com.likelion.fillyouinback.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@Setter
public class MemberDto {
  private String email;
  private String authority;
  private String firstName;
  private String lastName;
  private Integer semester;
  private String department;
  private List<String> affiliations;
  private String googleProfilePictureUrl;
  private String profileImage;
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
        .affiliations(splitString(member.getAffiliations()))
        .googleProfilePictureUrl(member.getGoogleProfilePictureUrl())
        .profileImage(member.getProfileImage())
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
        .affiliations(request.getAffiliations())
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
