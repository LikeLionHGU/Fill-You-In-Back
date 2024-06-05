package com.likelion.fillyouinback.file.controller;

import com.likelion.fillyouinback.file.controller.response.FileUploadResponse;
import com.likelion.fillyouinback.s3.exception.S3ImageUploadException;
import com.likelion.fillyouinback.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController {
  private final S3Service s3Service;
  private static final String FILE_DIR = "files";

  @PostMapping("/api/fillyouin/files")
  public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file)
      throws S3ImageUploadException {
    return ResponseEntity.ok(
        FileUploadResponse.builder().fileUrl(s3Service.uploadFile(file, FILE_DIR)).build());
  }
}
