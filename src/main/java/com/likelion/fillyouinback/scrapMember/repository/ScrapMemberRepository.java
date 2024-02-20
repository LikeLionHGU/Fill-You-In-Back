package com.likelion.fillyouinback.scrapMember.repository;

import com.likelion.fillyouinback.scrapMember.domain.ScrapMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapMemberRepository extends JpaRepository <ScrapMember,Long> {
    Boolean existsByMemberIdAndScrapMemberId(Long memberId, Long scrapMemberId);
    void deleteByMemberIdAndScrapMemberId(Long memberId, Long scrapMemberId);

    List<ScrapMember> findAllByMemberId(Long memberId);
}
