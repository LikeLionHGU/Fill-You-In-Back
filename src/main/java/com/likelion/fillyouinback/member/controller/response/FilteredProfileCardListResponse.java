package com.likelion.fillyouinback.member.controller.response;

import com.likelion.fillyouinback.member.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class FilteredProfileCardListResponse {
  private List<ProfileCard> profileCards;

  public static FilteredProfileCardListResponse from(
      List<MemberDto> dtos, String skillFilter, String jobFilter, String fieldFilter) {
    return FilteredProfileCardListResponse.builder()
        .profileCards(
            dtos.stream()
                .map(dto -> ProfileCard.from(dto, skillFilter, jobFilter, fieldFilter))
                .toList())
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

    public static ProfileCard from(
        MemberDto dto, String skillFilter, String jobFilter, String fieldFilter) {
      ProfileCard profileCard =
          ProfileCard.builder()
              .id(dto.getId())
              .firstName(dto.getFirstName())
              .lastName(dto.getLastName())
              .department(dto.getDepartment())
              .semester(dto.getSemester())
              .profileImageUrl(dto.getProfileImageUrl())
              .isScrapped(dto.getIsScrapped())
              .build();

      if (skillFilter == null || skillFilter.isBlank()) { // skillFilter가 없거나 비어있으면
        for (int i = 0; i < dto.getSkills().size(); i++) { // dto의 skills를 돌면서
          if (dto.getSkills().get(i).getIsPinned()) { // isPinned가 true인 skill을 찾아서
            profileCard.setSkill(dto.getSkills().get(i).getName()); // profileCard에 set해준다.
            break;
          }
        }
      } else {
        profileCard.setSkill(skillFilter); // skillFilter가 있으면 profileCard에 set해준다.
      }

      if (jobFilter == null || jobFilter.isBlank()) { // jobFilter가 없거나 비어있으면
        for (int i = 0; i < dto.getJobs().size(); i++) { // dto의 jobs를 돌면서
          if (dto.getJobs().get(i).getIsPinned()) { // isPinned가 true인 job을 찾아서
            profileCard.setJob(dto.getJobs().get(i).getName()); // profileCard에 set해준다.
            break;
          }
        }
      } else {
        profileCard.setJob(jobFilter); // jobFilter가 있으면 profileCard에 set해준다.
      }

      if (fieldFilter == null || fieldFilter.isBlank()) { // fieldFilter가 없거나 비어있으면
        for (int i = 0; i < dto.getFields().size(); i++) { // dto의 fields를 돌면서
          if (dto.getFields().get(i).getIsPinned()) { // isPinned가 true인 field를 찾아서
            profileCard.setField(dto.getFields().get(i).getName()); // profileCard에 set해준다.
            break;
          }
        }
      } else {
        profileCard.setField(fieldFilter); // fieldFilter가 있으면 profileCard에 set해준다.
      }
      return profileCard;
    }
  }
}
