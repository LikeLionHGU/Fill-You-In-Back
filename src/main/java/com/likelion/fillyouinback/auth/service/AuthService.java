package com.likelion.fillyouinback.auth.service;

import com.likelion.fillyouinback.auth.dto.AuthDto;
import com.likelion.fillyouinback.auth.exception.FailedToGoogleLoginException;
import com.likelion.fillyouinback.auth.util.JwtUtil;
import com.likelion.fillyouinback.category.domain.Category;
import com.likelion.fillyouinback.category.repository.CategoryRepository;
import com.likelion.fillyouinback.member.domain.Member;
import com.likelion.fillyouinback.member.domain.enums.MemberAuthority;
import com.likelion.fillyouinback.member.repository.MemberRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

  private final MemberRepository memberRepository;

  private final GoogleIdTokenVerifier verifier;

  private final CategoryRepository categoryRepository;

  @Value("${custom.jwt.secret}")
  private String SECRET_KEY;

  @Value("${custom.jwt.expire-time-ms}")
  private long EXPIRE_TIME_MS;

  public Member getLoginMember(Long id) {
    return memberRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
  }

  public AuthDto loginOAuthGoogle(AuthDto dto) {
    Member member = verifyIDToken(dto.getGoogleIdToken());
    member = createOrUpdateUser(member);
    return AuthDto.builder()
        .jwt(JwtUtil.createToken(member.getId(), SECRET_KEY, EXPIRE_TIME_MS))
        .build();
  }

  public Member createOrUpdateUser(Member member) {
    Member existingMember = memberRepository.findByEmail(member.getEmail());
    if (existingMember == null) {
      member.setAuthority(MemberAuthority.USER);
      memberRepository.save(member);
      categoryRepository.save(Category.from(member, "1학년"));
      categoryRepository.save(Category.from(member, "2학년"));
      categoryRepository.save(Category.from(member, "3학년"));
      categoryRepository.save(Category.from(member, "4학년"));
      return member;
    }
    existingMember.setFirstName(member.getFirstName());
    existingMember.setLastName(member.getLastName());
    existingMember.setGoogleProfilePictureUrl(member.getGoogleProfilePictureUrl());
    return existingMember;
  }

  private Member verifyIDToken(String idToken) {
    try {
      GoogleIdToken idTokenObj = verifier.verify(idToken);
      if (idTokenObj == null) {
        throw new FailedToGoogleLoginException();
      }
      return Member.from(idTokenObj.getPayload());
    } catch (Exception e) {
      throw new FailedToGoogleLoginException();
    }
  }
}
