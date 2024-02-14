package com.likelion.fillyouinback.member.controller;

import com.likelion.fillyouinback.auth.util.JwtUtil;
import com.likelion.fillyouinback.member.controller.request.UpdateMyProfileRequest;
import com.likelion.fillyouinback.member.controller.response.MyProfileResponse;
import com.likelion.fillyouinback.member.dto.MemberDto;
import com.likelion.fillyouinback.s3.exception.S3ImageUploadException;
import com.likelion.fillyouinback.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.likelion.fillyouinback.member.service.MemberService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MemberController {

  @Value("$${custom.jwt.secret}")
  private String SECRET_KEY;

  private final MemberService memberService;

  private final S3Service s3Service;
  private static final String PROFILE_IMAGE_DIR = "profile";

  private static final String BANNER_IMAGE_DIR = "banner";

  @GetMapping("/api/fillyouin/my-profile")
  public ResponseEntity<MyProfileResponse> getMemberProfile(
      @RequestHeader("Authorization") String bearerToken) {
    MemberDto memberDto =
        memberService.getMember(JwtUtil.getMemberId(getToken(bearerToken), SECRET_KEY));
    MyProfileResponse response =
        MyProfileResponse.from(
                memberDto);
    response.setProfileImageUrl(
        s3Service.getImageUrl(PROFILE_IMAGE_DIR, memberDto.getProfileImage()));
    return ResponseEntity.ok(response);
  }

  @PutMapping("/api/fillyouin/my-profile")
  public void updateMemberProfile(
      @RequestHeader("Authorization") String bearerToken,
      @RequestBody UpdateMyProfileRequest request) {
    memberService.updateMember(
        JwtUtil.getMemberId(getToken(bearerToken), SECRET_KEY), MemberDto.from(request));
  }

  @PostMapping("/api/fillyouin/my-profile/profile-image")
  public void uploadProfileImage(
      @RequestHeader("Authorization") String bearerToken,
      @RequestParam("image") MultipartFile image)
      throws S3ImageUploadException {
    String fileName = s3Service.upload(image, PROFILE_IMAGE_DIR);
    MemberDto memberDto = MemberDto.builder().profileImage(fileName).build();
    memberDto.setProfileImage(fileName);
    memberService.updateMember(JwtUtil.getMemberId(getToken(bearerToken), SECRET_KEY), memberDto);
  }

  private static String getToken(String bearerToken) {
    return bearerToken.split(" ")[1];
  }
}
