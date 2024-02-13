package com.likelion.fillyouinback.member.repository;

import com.likelion.fillyouinback.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByEmail(String email);
}
