package com.likelion.fillyouinback.memberSkill.dto;

import com.likelion.fillyouinback.member.controller.request.UpdateMyProfileRequest;
import com.likelion.fillyouinback.memberSkill.domain.MemberSkill;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@Setter
public class MemberSkillDto {
    private String name;
    private Boolean isPinned;

    public static MemberSkillDto from(MemberSkill memberSkill) {
        return MemberSkillDto.builder()
            .name(memberSkill.getName())
            .isPinned(memberSkill.getIsPinned())
            .build();
    }

    public static List<MemberSkillDto> listFrom(UpdateMyProfileRequest request) {
        List<MemberSkillDto> skills = request.getSkills().stream()
            .map(skill -> MemberSkillDto.builder()
                .name(skill.getName())
                .isPinned(skill.getIsPinned())
                .build())
            .toList();
        setOnePin(skills);
        return skills;
    }

    private static void setOnePin(List<MemberSkillDto> skills) {
        if (skills.isEmpty()) {
            return;
        }
        for (MemberSkillDto skill : skills) {
            if (skill.getIsPinned()) {
                return;
            }
        }
        skills.get(0).setIsPinned(true);
    }
}
