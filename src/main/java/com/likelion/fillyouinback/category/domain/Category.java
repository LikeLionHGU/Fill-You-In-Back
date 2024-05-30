package com.likelion.fillyouinback.category.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.category.dto.CategoryDto;
import com.likelion.fillyouinback.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "name", length = 60, nullable = false)
    private String name;


    public static Category from(Member member, CategoryDto dto) {
        return Category.builder()
            .member(member)
            .name(dto.getName())
            .build();
    }
}
