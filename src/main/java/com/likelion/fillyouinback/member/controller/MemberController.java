package com.likelion.fillyouinback.member.controller;

import com.likelion.fillyouinback.auth.util.JwtUtil;
import com.likelion.fillyouinback.member.controller.request.UpdateMyProfileRequest;
import com.likelion.fillyouinback.member.controller.response.MyProfileResponse;
import com.likelion.fillyouinback.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.likelion.fillyouinback.member.service.MemberService;
@RestController
@RequiredArgsConstructor
public class MemberController {

    @Value("$${custom.jwt.secret}")
    private String SECRET_KEY;

    private final MemberService memberService;

    @GetMapping("/api/fillyouin/my-profile")
    public ResponseEntity<MyProfileResponse> getMemberProfile(@RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(MyProfileResponse.from(memberService.getMember(JwtUtil.getMemberId(bearerToken.split(" ")[1], SECRET_KEY))));
    }
    @PutMapping("/api/fillyouin/my-profile")
    public void updateMemberProfile(@RequestHeader("Authorization") String bearerToken, @RequestBody UpdateMyProfileRequest request) {
        memberService.updateMember(JwtUtil.getMemberId(bearerToken.split(" ")[1], SECRET_KEY), MemberDto.from(request));
    }
}
