package com.likelion.fillyouinback.member.repository;

import com.likelion.fillyouinback.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository extends JpaRepository<Member, Long> , JpaSpecificationExecutor<Member> {
  Member findByEmail(String email);
}
