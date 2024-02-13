package com.likelion.fillyouinback.member.service;

import com.likelion.fillyouinback.base.exception.NotFoundException;
import com.likelion.fillyouinback.member.domain.Member;
import com.likelion.fillyouinback.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.likelion.fillyouinback.member.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  public MemberDto getMember(Long memberId) {
    return MemberDto.from(
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다.")));
  }

  public void updateMember(Long memberId, MemberDto dto) {
    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
    member.update(dto);
  }
}
