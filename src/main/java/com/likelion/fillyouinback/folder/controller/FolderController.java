package com.likelion.fillyouinback.folder.controller;

import com.likelion.fillyouinback.folder.controller.request.AddFolderRequest;
import com.likelion.fillyouinback.folder.controller.request.UpdateFolderRequest;
import com.likelion.fillyouinback.folder.controller.response.FolderListResponse;
import com.likelion.fillyouinback.folder.dto.FolderDto;
import com.likelion.fillyouinback.folder.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FolderController {
  private final FolderService folderService;

  @PostMapping("/api/fillyouin/folders")
  public ResponseEntity<Void> createFolder(@RequestBody AddFolderRequest request) {
    folderService.addFolder(FolderDto.from(request));
    return ResponseEntity.ok().build();
  }

  @GetMapping("/api/fillyouin/categories/{categoryId}/folders")
  public ResponseEntity<FolderListResponse> getFolderList(@PathVariable Long categoryId) {
    return ResponseEntity.ok(FolderListResponse.from(folderService.getFolderList(categoryId)));
  }

  @DeleteMapping("/api/fillyouin/folders/{id}")
  public ResponseEntity<Void> deleteFolder(@PathVariable Long id) {
    folderService.deleteFolder(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/api/fillyouin/folders/{id}")
    public ResponseEntity<Void> updateFolder(@PathVariable Long id, @RequestBody UpdateFolderRequest request) {
        folderService.updateFolder(FolderDto.from(id, request));
        return ResponseEntity.ok().build();
    }
}
