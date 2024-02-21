package com.likelion.fillyouinback.member.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class UpdateMyProfileRequest {
    private Integer semester;
    private String department;
    private List<Affiliation> affiliations;
    private List<Field> fields;
    private List<Job> jobs;
    private List<Skill> skills;
    private String introduction;

    @Getter
    @NoArgsConstructor
    public static class Affiliation{
        private String name;
        private Boolean isPinned;
    }

    @Getter
    @NoArgsConstructor
    public static class Field {
        private String name;
        private Boolean isPinned;
    }

    @Getter
    @NoArgsConstructor
    public static class Job {
        private String name;
        private Boolean isPinned;
    }

    @Getter
    @NoArgsConstructor
    public static class Skill {
        private String name;
        private Boolean isPinned;
    }
}
