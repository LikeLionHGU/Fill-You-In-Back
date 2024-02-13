package com.likelion.fillyouinback.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.likelion.fillyouinback.s3.exception.ImageSizeLimitException;
import com.likelion.fillyouinback.s3.exception.NotImageException;
import com.likelion.fillyouinback.s3.exception.S3ImageUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Service
public class S3Service {

  private final AmazonS3 amazonS3Client;

  @Value("${custom.aws.s3.bucket}")
  private String bucket;

  private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

  public String upload(MultipartFile multipartFile, String dirPath) throws S3ImageUploadException{
    if (multipartFile == null || multipartFile.isEmpty()) {
      return null;
    }
    validImageType(multipartFile);
    validFileSize(multipartFile);
    String fileName = getUuidFileName(multipartFile.getOriginalFilename());
    String filePath = dirPath + "/" + fileName;
    try{
      amazonS3Client.putObject(
              new PutObjectRequest(bucket, filePath, multipartFile.getInputStream(), null)
                      .withCannedAcl(CannedAccessControlList.PublicRead) // PublicRead 권한으로 업로드 됨
      );
    } catch (IOException e) {
      throw new S3ImageUploadException();
    }
    return fileName;
  }

  private void validImageType(MultipartFile multipartFile) {
    if (!Objects.requireNonNull(multipartFile.getContentType()).startsWith("image")) {
      throw new NotImageException();
    }
  }

  private void validFileSize(MultipartFile multipartFile) {
    if (multipartFile.getSize() > MAX_FILE_SIZE) {
      throw new ImageSizeLimitException(MAX_FILE_SIZE);
    }
  }

  public String getImageUrl(String dirPath, String fileName) {
    if (fileName == null) {
      return null;
    }
    return amazonS3Client.getUrl(bucket, dirPath + "/" + fileName).toString();
  }

  private static String getUuidFileName(String originalFileName) {
    return UUID.randomUUID() + "_" + originalFileName;
  }
}
