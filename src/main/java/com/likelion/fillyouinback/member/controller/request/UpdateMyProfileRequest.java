package com.likelion.fillyouinback.member.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class UpdateMyProfileRequest {
    private Integer semester;
    private String department;
    private String affiliation;
    private List<String> fields;
    private List<String> jobs;
    private List<String> skills;
    private String introduction;
}
