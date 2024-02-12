package com.likelion.fillyouinback.auth.controller;

import com.likelion.fillyouinback.auth.controller.request.LoginRequest;
import com.likelion.fillyouinback.auth.controller.response.LoginResponse;
import com.likelion.fillyouinback.auth.dto.AuthDto;
import com.likelion.fillyouinback.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fillyouin/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginWithGoogle(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(LoginResponse.from(authService.loginOAuthGoogle(AuthDto.from(request))));
    }
}
