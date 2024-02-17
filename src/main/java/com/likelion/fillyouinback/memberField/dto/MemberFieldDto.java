package com.likelion.fillyouinback.memberField.dto;

import com.likelion.fillyouinback.member.controller.request.UpdateMyProfileRequest;
import com.likelion.fillyouinback.memberField.domain.MemberField;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@Setter
public class MemberFieldDto {
    private String name;
    private Boolean isPinned;

    public static MemberFieldDto from(MemberField memberField) {
        return MemberFieldDto.builder()
            .name(memberField.getName())
            .isPinned(memberField.getIsPinned())
            .build();
    }

    public static List<MemberFieldDto> listFrom(UpdateMyProfileRequest request) {
        List<MemberFieldDto> fields = request.getFields().stream()
            .map(field -> MemberFieldDto.builder()
                .name(field.getName())
                .isPinned(field.getIsPinned())
                .build())
            .toList();
        setOnePin(fields);
        return fields;
    }

    private static void setOnePin(List<MemberFieldDto> fields) {
        if (fields.isEmpty()) {
            return;
        }
        for (MemberFieldDto field : fields) {
            if (field.getIsPinned()) {
                return;
            }
        }
        fields.get(0).setIsPinned(true);
    }
}
