package com.likelion.fillyouinback.category.controller;

import com.likelion.fillyouinback.category.controller.request.AddCategoryRequest;
import com.likelion.fillyouinback.category.controller.request.UpdateCategoryRequest;
import com.likelion.fillyouinback.category.controller.response.CategoryListResponse;
import com.likelion.fillyouinback.category.dto.CategoryDto;
import com.likelion.fillyouinback.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

  @PatchMapping("/api/fillyouin/categories/{categoryId}")
  public ResponseEntity<Void> updateCategory(
      @PathVariable Long categoryId, @RequestBody UpdateCategoryRequest request) {
    categoryService.updateCategory(CategoryDto.from(request, categoryId));
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/api/fillyouin/categories/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
    categoryService.deleteCategory(categoryId);
    return ResponseEntity.ok().build();
  }
}
