package com.likelion.fillyouinback.field.dto;

import com.likelion.fillyouinback.field.domain.Field;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FieldDto {
  private String name;

  public static FieldDto from(Field field) {
    return FieldDto.builder().name(field.getName()).build();
  }
}
