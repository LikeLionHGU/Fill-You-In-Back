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
    this.semester = dto.getSemester();
    this.department = dto.getDepartment();
    this.affiliations = String.join(",", dto.getAffiliations());
    this.fields = String.join(",", dto.getFields());
    this.jobs = String.join(",", dto.getJobs());
    this.skills = String.join(",", dto.getSkills());
    this.introduction = dto.getIntroduction();
    this.profileImage = dto.getProfileImage();
  }
}
