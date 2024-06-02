package com.likelion.fillyouinback.folder.dto;

import com.likelion.fillyouinback.category.dto.CategoryDto;
import com.likelion.fillyouinback.folder.controller.request.AddFolderRequest;
import com.likelion.fillyouinback.folder.domain.Folder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FolderDto {
  private Long id;
  private String name;
  private CategoryDto categoryDto;

  public static FolderDto from(AddFolderRequest request) {
    return FolderDto.builder()
        .name(request.getName())
        .categoryDto(CategoryDto.builder().id(request.getCategoryId()).build())
        .build();
  }

  public static FolderDto from(Folder folder) {
    return FolderDto.builder().id(folder.getId()).name(folder.getName()).build();
  }
}
