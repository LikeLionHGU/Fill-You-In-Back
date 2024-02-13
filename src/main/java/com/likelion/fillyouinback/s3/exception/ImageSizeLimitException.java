package com.likelion.fillyouinback.s3.exception;

public class ImageSizeLimitException extends RuntimeException{
    public ImageSizeLimitException(Long size) {
        super("이미지는 " + size + "byte 이하여야 합니다.");
    }
}
