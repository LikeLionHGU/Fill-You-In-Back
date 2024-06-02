package com.likelion.fillyouinback.folder.controller;

import com.likelion.fillyouinback.folder.dto.FolderDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FolderListResponse {
  private List<Folder> folders;

  public static FolderListResponse from(List<FolderDto> dtos) {
    return FolderListResponse.builder().folders(dtos.stream().map(Folder::from).toList()).build();
  }

  @Builder
  @Getter
  private static class Folder {
    private Long id;
    private String name;

    public static Folder from(FolderDto dto) {
      return Folder.builder().id(dto.getId()).name(dto.getName()).build();
    }
  }
}
