package com.likelion.fillyouinback.member.domain;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.member.domain.enums.MemberAuthority;
import com.likelion.fillyouinback.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(name = "email", length = 320, nullable = false)
  private String email;

  @Column(name = "authority", length = 30, nullable = false)
  @Enumerated(EnumType.STRING)
  private MemberAuthority authority;

  @Column(name = "first_name", length = 60)
  private String firstName;

  @Column(name = "last_name", length = 60)
  private String lastName;

  @Column(name = "semester")
  private Integer semester;

  @Column(name = "department", length = 60)
  private String department;

  @Column(name = "google_profile_picture_url", length = 100, nullable = false)
  private String googleProfilePictureUrl;

  @Column(name = "profile_image", length = 500)
  private String profileImage;

  @Column(name = "affiliations", columnDefinition = "TEXT")
  private String affiliations;

  @Column(name = "fields", columnDefinition = "TEXT")
  private String fields;

  @Column(name = "jobs", columnDefinition = "TEXT")
  private String jobs;

  @Column(name = "skills", columnDefinition = "TEXT")
  private String skills;

  @Column(name = "introduction", columnDefinition = "TEXT")
  private String introduction;

  public static Member from(GoogleIdToken.Payload payload) {
    return Member.builder()
        .email(payload.getEmail())
        .authority(MemberAuthority.USER)
        .firstName((String) payload.get("given_name"))
        .lastName((String) payload.get("family_name"))
        .googleProfilePictureUrl((String) payload.get("picture"))
        .build();
  }

  public void update(MemberDto dto) {
    this.semester = dto.getSemester() != null ? dto.getSemester() : this.semester;
    this.department = dto.getDepartment() != null ? dto.getDepartment() : this.department;
    this.affiliations = dto.getAffiliations() != null ? String.join(",", dto.getAffiliations()) : this.affiliations;
    this.fields = dto.getFields() != null ? String.join(",", dto.getFields()) : this.fields;
    this.jobs = dto.getJobs() != null ? String.join(",", dto.getJobs()) : this.jobs;
    this.skills = dto.getSkills() != null ? String.join(",", dto.getSkills()) : this.skills;
    this.introduction = dto.getIntroduction() != null ? dto.getIntroduction() : this.introduction;
    this.profileImage = dto.getProfileImage() != null ? dto.getProfileImage() : this.profileImage;
  }
}
