package com.likelion.fillyouinback.member.controller;

import com.likelion.fillyouinback.auth.util.JwtUtil;
import com.likelion.fillyouinback.member.controller.response.MyProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
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
//    @PutMapping("/api/fillyouin/members/my-profile")
//    public void updateMemberProfile(@RequestHeader("Authorization") String bearerToken) {
//        memberService.updateMember(JwtUtil.getMemberId(bearerToken, SECRET_KEY));
//    }
}
