package com.likelion.fillyouinback.field.controller.response;

import java.util.List;

import com.likelion.fillyouinback.field.dto.FieldDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FieldListResponse {
  private final List<Field> fields;

  public FieldListResponse(List<FieldDto> dtos) {
    this.fields = dtos.stream().map(Field::from).toList();
  }

  @Builder
  @Getter
  private static class Field {
    private String name;

    public static Field from(FieldDto dto) {
      return Field.builder().name(dto.getName()).build();
    }
  }
}
