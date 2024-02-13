package com.likelion.fillyouinback.auth.controller.response;

import com.likelion.fillyouinback.auth.dto.AuthDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
  private String jwt;

  public static LoginResponse from(AuthDto dto) {
    return LoginResponse.builder().jwt(dto.getJwt()).build();
  }
}
