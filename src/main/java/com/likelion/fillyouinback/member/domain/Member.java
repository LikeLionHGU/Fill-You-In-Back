package com.likelion.fillyouinback.member.domain;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.member.domain.enums.MemberAuthority;
import com.likelion.fillyouinback.member.dto.MemberDto;
import com.likelion.fillyouinback.memberField.domain.MemberField;
import com.likelion.fillyouinback.memberJob.domain.MemberJob;
import com.likelion.fillyouinback.memberSkill.domain.MemberSkill;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

  @Column(name = "google_profile_picture_url", length = 1000, nullable = false)
  private String googleProfilePictureUrl;

  @Column(name = "profile_image", length = 500)
  private String profileImage;

  @Column(name = "affiliations", columnDefinition = "TEXT")
  private String affiliations;

  @Column(name = "introduction", columnDefinition = "TEXT")
  private String introduction;

  @OneToMany(
      mappedBy = "member",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<MemberSkill> skills;

  @OneToMany(
      mappedBy = "member",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<MemberField> fields;

  @OneToMany(
      mappedBy = "member",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<MemberJob> jobs;

  @Column(name = "is_first_profile_visit", nullable = false)
  private Boolean isFirstProfileVisit;

  public static Member from(GoogleIdToken.Payload payload) {
    return Member.builder()
        .email(payload.getEmail())
        .authority(MemberAuthority.USER)
        .firstName((String) payload.get("given_name"))
        .lastName((String) payload.get("family_name"))
        .googleProfilePictureUrl((String) payload.get("picture"))
        .isFirstProfileVisit(true)
        .build();
  }

  public void update(MemberDto dto) {
    this.semester = dto.getSemester();
    this.department = dto.getDepartment();
    this.affiliations = String.join(",", dto.getAffiliations());
    this.introduction = dto.getIntroduction();
    this.skills.clear();
    this.skills.addAll(MemberSkill.listFrom(dto.getSkills(), this));
    this.fields.clear();
    this.fields.addAll(MemberField.listFrom(dto.getFields(), this));
    this.jobs.clear();
    this.jobs.addAll(MemberJob.listFrom(dto.getJobs(), this));
  }
}
