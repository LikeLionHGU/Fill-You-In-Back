package com.likelion.fillyouinback.member.dto;

import com.likelion.fillyouinback.member.controller.request.UpdateMyProfileRequest;
import com.likelion.fillyouinback.member.domain.Member;
import com.likelion.fillyouinback.memberField.dto.MemberFieldDto;
import com.likelion.fillyouinback.memberJob.dto.MemberJobDto;
import com.likelion.fillyouinback.memberSkill.dto.MemberSkillDto;
import com.likelion.fillyouinback.scrapMember.domain.ScrapMember;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@Setter
public class MemberDto {
  private Long id;
  private String email;
  private String authority;
  private String firstName;
  private String lastName;
  private Integer semester;
  private String department;
  private List<String> affiliations;
  private String googleProfilePictureUrl;
  private String profileImage;
  private String profileImageUrl;
  private List<MemberFieldDto> fields;
  private List<MemberJobDto> jobs;
  private List<MemberSkillDto> skills;
  private String introduction;
  private Boolean isFirstProfileVisit;
  private Boolean isScrapped;

  public static MemberDto from(Member member) {
    return MemberDto.builder()
        .id(member.getId())
        .email(member.getEmail())
        .authority(member.getAuthority().getKorean())
        .firstName(member.getFirstName())
        .lastName(member.getLastName())
        .semester(member.getSemester())
        .department(member.getDepartment())
        .affiliations(splitString(member.getAffiliations()))
        .googleProfilePictureUrl(member.getGoogleProfilePictureUrl())
        .profileImage(member.getProfileImage())
        .fields(member.getFields().stream().map(MemberFieldDto::from).toList())
        .jobs(member.getJobs().stream().map(MemberJobDto::from).toList())
        .skills(member.getSkills().stream().map(MemberSkillDto::from).toList())
        .introduction(member.getIntroduction())
        .isFirstProfileVisit(member.getIsFirstProfileVisit())
        .build();
  }

  public static MemberDto from(UpdateMyProfileRequest request) {
    return MemberDto.builder()
        .semester(request.getSemester())
        .department(request.getDepartment())
        .affiliations(request.getAffiliations().stream().map(UpdateMyProfileRequest.Affiliation::getName).toList())
        .fields(MemberFieldDto.listFrom(request))
        .jobs(MemberJobDto.listFrom(request))
        .skills(MemberSkillDto.listFrom(request))
        .introduction(request.getIntroduction())
        .build();
  }

  public static MemberDto from(Member member, List<ScrapMember> scrapMembers){
    MemberDto memberDto = from(member);
    memberDto.setIsScrapped(scrapMembers.stream().anyMatch(scrapMember -> scrapMember.getScrapMember().getId().equals(member.getId())));
    return memberDto;
  }

  private static List<String> splitString(String str) {
    return str == null ? null : List.of(str.split(","));
  }
}
