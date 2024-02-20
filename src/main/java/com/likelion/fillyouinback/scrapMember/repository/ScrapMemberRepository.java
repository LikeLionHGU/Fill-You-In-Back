package com.likelion.fillyouinback.scrapMember.repository;

import com.likelion.fillyouinback.scrapMember.domain.ScrapMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScrapMemberRepository extends JpaRepository <ScrapMember,Long> {
    Boolean existsByMemberIdAndScrapMemberId(Long memberId, Long scrapMemberId);
    void deleteByMemberIdAndScrapMemberId(Long memberId, Long scrapMemberId);

    List<ScrapMember> findAllByMemberId(Long memberId);

    @Query("SELECT distinct sm FROM ScrapMember sm JOIN FETCH sm.scrapMember m WHERE sm.member.id = :memberId")
    List<ScrapMember> findAllByMemberIdWithScrapMember(Long memberId);


}