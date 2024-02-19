package com.likelion.fillyouinback.member.service;

import com.likelion.fillyouinback.base.exception.NotFoundException;
import com.likelion.fillyouinback.member.domain.Member;
import com.likelion.fillyouinback.member.dto.MemberDto;
import com.likelion.fillyouinback.member.filter.MemberSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.likelion.fillyouinback.member.repository.MemberRepository;

import java.util.List;

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

  public void updateMemberProfileImage(Long memberId, MemberDto memberDto) {
    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
    member.setProfileImage(memberDto.getProfileImage());
  }

  public void updateIsFirstProfileVisit(Long memberId, Boolean isFirstProfileVisit) {
    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
    member.setIsFirstProfileVisit(isFirstProfileVisit);
  }

  public List<MemberDto> getFilteredMembers(
      Long memberId,
      String name,
      String department,
      Integer semester,
      String skill,
      String job,
      String field) {

    Specification<Member> spec = (root, query, criteriaBuilder) -> null;

    if (name != null && !name.isBlank()) {
      spec = spec.and(MemberSpecification.hasName(name));
    }

    if (department != null && !department.isBlank()) {
      spec = spec.and(MemberSpecification.hasDepartment(department));
    }

    if (semester != null) {
      spec = spec.and(MemberSpecification.hasSemester(semester));
    }

    if (skill != null && !skill.isBlank()) {
      spec = spec.and(MemberSpecification.hasSkill(skill));
    }

    if (job != null && !job.isBlank()) {
      spec = spec.and(MemberSpecification.hasJob(job));
    }

    if (field != null && !field.isBlank()) {
      spec = spec.and(MemberSpecification.hasField(field));
    }

    spec = spec.and(MemberSpecification.notMe(memberId));

    return memberRepository.findAll(spec).stream().map(MemberDto::from).toList();
  }
}
