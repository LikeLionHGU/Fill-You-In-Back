package com.likelion.fillyouinback.member.controller;

import com.likelion.fillyouinback.member.controller.request.UpdateMyProfileRequest;
import com.likelion.fillyouinback.member.controller.response.*;
import com.likelion.fillyouinback.member.dto.MemberDto;
import com.likelion.fillyouinback.s3.exception.S3ImageUploadException;
import com.likelion.fillyouinback.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.likelion.fillyouinback.member.service.MemberService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  private final S3Service s3Service;
  private static final String PROFILE_IMAGE_DIR = "profile";

  @GetMapping("/api/fillyouin/my-profile")
  public ResponseEntity<MyProfileResponse> getMyProfile(@AuthenticationPrincipal Long memberId) {
    MemberDto memberDto = memberService.getMember(memberId);
    memberDto.setProfileImageUrl(
        s3Service.getImageUrl(PROFILE_IMAGE_DIR, memberDto.getProfileImage()));
    MyProfileResponse response = MyProfileResponse.from(memberDto);
    memberService.updateIsFirstProfileVisit(memberId, false);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/api/fillyouin/members/profile-card")
  public ResponseEntity<FilteredProfileCardListResponse> getProfileCard(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String department,
      @RequestParam(required = false) Integer semester,
      @RequestParam(required = false) String skill,
      @RequestParam(required = false) String job,
      @RequestParam(required = false) String field,
      @AuthenticationPrincipal Long memberId) {
    List<MemberDto> memberDtos =
        memberService.getFilteredMembers(memberId, name, department, semester, skill, job, field);
    memberDtos.forEach(
        memberDto ->
            memberDto.setProfileImageUrl(
                s3Service.getImageUrl(PROFILE_IMAGE_DIR, memberDto.getProfileImage())));
    return ResponseEntity.ok(FilteredProfileCardListResponse.from(memberDtos, skill, job, field));
  }

  @GetMapping("/api/fillyouin/members/my-simple-profile-card")
  public ResponseEntity<SimpleProfileCardResponse> getMySimpleProfileCard(
      @AuthenticationPrincipal Long memberId) {
    MemberDto memberDto = memberService.getMember(memberId);
    memberDto.setProfileImageUrl(
        s3Service.getImageUrl(PROFILE_IMAGE_DIR, memberDto.getProfileImage()));
    return ResponseEntity.ok(SimpleProfileCardResponse.from(memberDto));
  }

  @GetMapping("/api/fillyouin/members/scrap-profile-card")
  public ResponseEntity<ScrapProfileCardListResponse> getScrapProfileCard(
      @AuthenticationPrincipal Long memberId) {
    List<MemberDto> memberDtos = memberService.getScrapMembers(memberId);
    memberDtos.forEach(
        memberDto ->
            memberDto.setProfileImageUrl(
                s3Service.getImageUrl(PROFILE_IMAGE_DIR, memberDto.getProfileImage())));
    return ResponseEntity.ok(ScrapProfileCardListResponse.from(memberDtos));
  }

  @GetMapping("/api/fillyouin/members/{memberId}/profile")
  public ResponseEntity<MemberProfileResponse> getMemberProfile(
      @PathVariable Long memberId, @AuthenticationPrincipal Long myId) {
    MemberDto memberDto = memberService.getMember(myId, memberId);
    memberDto.setProfileImageUrl(
        s3Service.getImageUrl(PROFILE_IMAGE_DIR, memberDto.getProfileImage()));
    return ResponseEntity.ok(MemberProfileResponse.from(memberDto));
  }

  @PutMapping("/api/fillyouin/my-profile")
  public void updateMemberProfile(
      @AuthenticationPrincipal Long memberId, @RequestBody UpdateMyProfileRequest request) {
    memberService.updateMember(memberId, MemberDto.from(request));
  }

  @PostMapping("/api/fillyouin/my-profile/profile-image")
  public void uploadProfileImage(
      @AuthenticationPrincipal Long memberId, @RequestParam("image") MultipartFile image)
      throws S3ImageUploadException {
    String fileName = s3Service.upload(image, PROFILE_IMAGE_DIR);
    MemberDto memberDto = MemberDto.builder().profileImage(fileName).build();
    memberDto.setProfileImage(fileName);
    memberService.updateMemberProfileImage(memberId, memberDto);
  }
}
