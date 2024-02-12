package com.likelion.fillyouinback.auth.dto;

import com.likelion.fillyouinback.auth.controller.request.LoginRequest;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AuthDto {
  private String googleIdToken;
  private String jwt;

  public static AuthDto from(LoginRequest request) {
    return AuthDto.builder().googleIdToken(request.getGoogleIdToken()).build();
  }
}
