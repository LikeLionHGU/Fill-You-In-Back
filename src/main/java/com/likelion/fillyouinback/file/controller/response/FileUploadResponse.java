package com.likelion.fillyouinback.file.controller.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FileUploadResponse {
    private String fileUrl;
}
