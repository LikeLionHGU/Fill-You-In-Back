package com.likelion.fillyouinback.member.controller.response;

import com.likelion.fillyouinback.member.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimpleProfileCardResponse {
    private String firstName;
    private String lastName;
    private Integer semester;
    private String department;
    private String profileImageUrl;
    private String email;

    public static SimpleProfileCardResponse from(MemberDto dto){
        return SimpleProfileCardResponse.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .semester(dto.getSemester())
                .department(dto.getDepartment())
                .profileImageUrl(dto.getProfileImageUrl())
                .email(dto.getEmail())
                .build();
    }
}
