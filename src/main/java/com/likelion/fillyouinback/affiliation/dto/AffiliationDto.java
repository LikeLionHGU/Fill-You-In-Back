package com.likelion.fillyouinback.affiliation.dto;

import com.likelion.fillyouinback.affiliation.domain.Affiliation;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AffiliationDto {
  private String name;

  public static AffiliationDto from(Affiliation affiliation) {
    return AffiliationDto.builder().name(affiliation.getName()).build();
  }
}
