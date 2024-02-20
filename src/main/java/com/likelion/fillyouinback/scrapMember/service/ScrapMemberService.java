package com.likelion.fillyouinback.scrapMember.service;

import com.likelion.fillyouinback.base.exception.NotFoundException;
import com.likelion.fillyouinback.member.domain.Member;
import com.likelion.fillyouinback.member.repository.MemberRepository;
import com.likelion.fillyouinback.scrapMember.domain.ScrapMember;
import com.likelion.fillyouinback.scrapMember.repository.ScrapMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ScrapMemberService {
  private final ScrapMemberRepository scrapMemberRepository;
  private final MemberRepository memberRepository;

  public void scrapMember(Long memberId, Long scrapMemberId) {
    if (!scrapMemberRepository.existsByMemberIdAndScrapMemberId(memberId, scrapMemberId)
        && !Objects.equals(memberId, scrapMemberId)) {
      Member member = getMember(memberId);
      Member scrapMember = getMember(scrapMemberId);
      scrapMemberRepository.save(ScrapMember.from(member, scrapMember));
    }
  }

  private Member getMember(Long memberId) {
    return memberRepository
        .findById(memberId)
        .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
  }

  public void deleteScrapMember(Long memberId, Long scrapMemberId) {
    scrapMemberRepository.deleteByMemberIdAndScrapMemberId(memberId, scrapMemberId);
  }
}
