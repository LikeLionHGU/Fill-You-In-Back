package com.likelion.fillyouinback.member.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.member.domain.enums.MemberAuthority;
import jakarta.persistence.*;

@Entity
public class member extends BaseTime {
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
  private int semester;

  @Column(name = "department", length = 60)
  private String department;

  @Column(name = "affiliation", columnDefinition = "TEXT")
  private String affiliation;

  @Column(name = "google_profile_picture_url", length = 100, nullable = false)
  private String googleProfilePictureUrl;

  @Column(name = "profile_image", length = 500)
  private String profileImage;

  @Column(name = "banner_image", length = 500)
  private String bannerImage;

  @Column(name = "fields", columnDefinition = "TEXT")
  private String fields;

  @Column(name = "jobs", columnDefinition = "TEXT")
  private String jobs;

  @Column(name = "skills", columnDefinition = "TEXT")
  private String skills;
}
