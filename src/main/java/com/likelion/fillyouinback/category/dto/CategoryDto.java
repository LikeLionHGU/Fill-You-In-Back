package com.likelion.fillyouinback.category.dto;

import com.likelion.fillyouinback.category.controller.request.AddCategoryRequest;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDto {
  private Long id;
  private Long memberId;
  private String name;

  public static CategoryDto from(AddCategoryRequest request, Long memberId) {
    return CategoryDto.builder().name(request.getName()).memberId(memberId).build();
  }
}
