package com.likelion.fillyouinback.memberField.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.member.domain.Member;
import com.likelion.fillyouinback.memberField.dto.MemberFieldDto;
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
public class MemberField extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_field_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "is_pinned" , nullable = false)
    private Boolean isPinned;

    public static List<MemberField> listFrom(List<MemberFieldDto> fields, Member member) {
        return fields.stream()
            .map(field -> MemberField.builder()
                .name(field.getName())
                .isPinned(field.getIsPinned())
                .member(member)
                .build())
            .toList();
    }
}
