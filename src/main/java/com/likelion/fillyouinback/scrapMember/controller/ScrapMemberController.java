package com.likelion.fillyouinback.scrapMember.controller;

import com.likelion.fillyouinback.auth.util.JwtUtil;
import com.likelion.fillyouinback.scrapMember.service.ScrapMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScrapMemberController {

  @Value("$${custom.jwt.secret}")
  private String SECRET_KEY;

  private final ScrapMemberService scrapMemberService;

  @PostMapping("/api/fillyouin/scrap-member")
  public ResponseEntity<Void> scrapMember(
      @RequestHeader("Authorization") String bearerToken, @RequestParam Long scrapMemberId) {
    Long memberId = JwtUtil.getMemberId(getToken(bearerToken), SECRET_KEY);
    scrapMemberService.scrapMember(memberId, scrapMemberId);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/api/fillyouin/scrap-member")
  public ResponseEntity<Void> deleteScrapMember(
      @RequestHeader("Authorization") String bearerToken, @RequestParam Long scrapMemberId) {
    Long memberId = JwtUtil.getMemberId(getToken(bearerToken), SECRET_KEY);
    scrapMemberService.deleteScrapMember(memberId, scrapMemberId);
    return ResponseEntity.ok().build();
  }

  private static String getToken(String bearerToken) {
    return bearerToken.split(" ")[1];
  }
}
