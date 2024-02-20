package com.likelion.fillyouinback.member.controller.response;

import com.likelion.fillyouinback.member.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ScrapProfileCardListResponse {
  private List<ProfileCard> profileCards;

  public static ScrapProfileCardListResponse from(List<MemberDto> dtos) {
    return ScrapProfileCardListResponse.builder()
        .profileCards(dtos.stream().map(ProfileCard::from).toList())
        .build();
  }

  @Builder
  @Getter
  @Setter
  private static class ProfileCard {
    private Long id;
    private String firstName;
    private String lastName;
    private String department;
    private Integer semester;
    private String profileImageUrl;
    private String skill;
    private String job;
    private String field;
    private Boolean isScrapped;

    private static ProfileCard from(MemberDto dto) {
      ProfileCard profileCard =
          ProfileCard.builder()
              .id(dto.getId())
              .firstName(dto.getFirstName())
              .lastName(dto.getLastName())
              .department(dto.getDepartment())
              .semester(dto.getSemester())
              .profileImageUrl(dto.getProfileImageUrl())
              .isScrapped(true)
              .build();
      for (int i = 0; i < dto.getSkills().size(); i++) {
        if (dto.getSkills().get(i).getIsPinned()) {
          profileCard.setSkill(dto.getSkills().get(i).getName());
          break;
        }
      }
      for (int i = 0; i < dto.getJobs().size(); i++) {
        if (dto.getJobs().get(i).getIsPinned()) {
          profileCard.setJob(dto.getJobs().get(i).getName());
          break;
        }
      }
      for (int i = 0; i < dto.getFields().size(); i++) {
        if (dto.getFields().get(i).getIsPinned()) {
          profileCard.setField(dto.getFields().get(i).getName());
          break;
        }
      }
      return profileCard;
    }
  }
}
