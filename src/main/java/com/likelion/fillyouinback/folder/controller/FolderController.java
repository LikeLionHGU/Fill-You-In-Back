package com.likelion.fillyouinback.folder.controller;

import com.likelion.fillyouinback.folder.controller.request.AddFolderRequest;
import com.likelion.fillyouinback.folder.dto.FolderDto;
import com.likelion.fillyouinback.folder.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FolderController {
  private final FolderService folderService;

  @PostMapping("/api/fillyouin/folders")
  public ResponseEntity<Void> createFolder(@RequestBody AddFolderRequest request) {
    folderService.addFolder(FolderDto.from(request));
    return ResponseEntity.ok().build();
  }
}
