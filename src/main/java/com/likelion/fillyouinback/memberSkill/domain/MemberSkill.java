package com.likelion.fillyouinback.memberSkill.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.member.domain.Member;
import com.likelion.fillyouinback.memberSkill.dto.MemberSkillDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSkill extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_skill_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "is_pinned" , nullable = false)
    private Boolean isPinned;

    public static List<MemberSkill> listFrom(List<MemberSkillDto> skills, Member member) {
        return skills.stream()
            .map(skill -> MemberSkill.builder()
                .name(skill.getName())
                .isPinned(skill.getIsPinned())
                .member(member)
                .build())
            .toList();
    }
}
