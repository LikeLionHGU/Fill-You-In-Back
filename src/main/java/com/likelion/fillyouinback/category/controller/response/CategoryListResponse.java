package com.likelion.fillyouinback.category.controller.response;

import com.likelion.fillyouinback.category.dto.CategoryDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class CategoryListResponse {
  private List<Category> categories;

  public static CategoryListResponse from(List<CategoryDto> dtos) {
    return CategoryListResponse.builder()
        .categories(dtos.stream().map(Category::from).collect(Collectors.toList()))
        .build();
  }

  @Builder
  @Getter
  private static class Category {
    private Long id;
    private String name;

    private static Category from(CategoryDto dto) {
      return Category.builder().id(dto.getId()).name(dto.getName()).build();
    }
  }
}
