package com.likelion.fillyouinback.memberJob.dto;

import com.likelion.fillyouinback.member.controller.request.UpdateMyProfileRequest;
import com.likelion.fillyouinback.memberJob.domain.MemberJob;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@Setter
public class MemberJobDto {
    private String name;
    private Boolean isPinned;
    
    public static MemberJobDto from(MemberJob memberJob) {
        return MemberJobDto.builder()
            .name(memberJob.getName())
            .isPinned(memberJob.getIsPinned())
            .build();
    }

    public static List<MemberJobDto> listFrom(UpdateMyProfileRequest request) {
        List<MemberJobDto> jobs = request.getJobs().stream()
            .map(job -> MemberJobDto.builder()
                .name(job.getName())
                .isPinned(job.getIsPinned())
                .build())
            .toList();
        setOnePin(jobs);
        return jobs;
    }

    private static void setOnePin(List<MemberJobDto> jobs) {
        if (jobs.isEmpty()) {
            return;
        }
        for (MemberJobDto job : jobs) {
            if (job.getIsPinned()) {
                return;
            }
        }
        jobs.get(0).setIsPinned(true);
    }
}
