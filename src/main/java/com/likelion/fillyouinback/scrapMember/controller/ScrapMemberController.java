package com.likelion.fillyouinback.scrapMember.controller;

import com.likelion.fillyouinback.scrapMember.service.ScrapMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScrapMemberController {

  private final ScrapMemberService scrapMemberService;

  @PostMapping("/api/fillyouin/scrap-member")
  public ResponseEntity<Void> scrapMember(
      @RequestParam Long scrapMemberId, @AuthenticationPrincipal Long memberId) {
    scrapMemberService.scrapMember(memberId, scrapMemberId);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/api/fillyouin/scrap-member")
  public ResponseEntity<Void> deleteScrapMember(
      @AuthenticationPrincipal Long memberId, @RequestParam Long scrapMemberId) {
    scrapMemberService.deleteScrapMember(memberId, scrapMemberId);
    return ResponseEntity.ok().build();
  }
}
