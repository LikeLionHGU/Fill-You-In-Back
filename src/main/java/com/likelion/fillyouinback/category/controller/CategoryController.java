package com.likelion.fillyouinback.category.controller;

import com.likelion.fillyouinback.category.controller.request.AddCategoryRequest;
import com.likelion.fillyouinback.category.controller.response.CategoryListResponse;
import com.likelion.fillyouinback.category.dto.CategoryDto;
import com.likelion.fillyouinback.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @PostMapping("/api/fillyouin/categories")
  public ResponseEntity<Void> createCategory(
      @RequestBody AddCategoryRequest request, @AuthenticationPrincipal Long memberId) {
    categoryService.addCategory(CategoryDto.from(request, memberId));
    return ResponseEntity.ok().build();
  }

  @GetMapping("/api/fillyouin/categories")
  public ResponseEntity<CategoryListResponse> getCategoryList(
      @AuthenticationPrincipal Long memberId) {
    return ResponseEntity.ok(CategoryListResponse.from(categoryService.getCategoryList(memberId)));
  }
}
