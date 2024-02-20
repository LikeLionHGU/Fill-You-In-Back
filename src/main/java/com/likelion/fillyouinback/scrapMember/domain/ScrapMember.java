package com.likelion.fillyouinback.scrapMember.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ScrapMember extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "scrap_member_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "scraped_member_id")
  private Member scrapMember;

  public static ScrapMember from(Member member, Member scrapMember) {
    return ScrapMember.builder().member(member).scrapMember(scrapMember).build();
  }
}
