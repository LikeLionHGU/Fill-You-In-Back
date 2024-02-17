package com.likelion.fillyouinback.memberJob.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.member.domain.Member;
import com.likelion.fillyouinback.memberJob.dto.MemberJobDto;
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
public class MemberJob extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_job_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "is_pinned" , nullable = false)
    private Boolean isPinned;

    public static List<MemberJob> listFrom(List<MemberJobDto> jobs, Member member) {
        return jobs.stream()
            .map(job -> MemberJob.builder()
                .name(job.getName())
                .isPinned(job.getIsPinned())
                .member(member)
                .build())
            .toList();
    }
}
