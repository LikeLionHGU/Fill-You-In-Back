package com.likelion.fillyouinback.affiliation.controller.response;

import com.likelion.fillyouinback.affiliation.dto.AffiliationDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AffiliationListResponse {
  private final List<Affiliation> affiliations;

  public AffiliationListResponse(List<AffiliationDto> dtos) {
    this.affiliations = dtos.stream().map(Affiliation::from).toList();
  }

  @Builder
  @Getter
  private static class Affiliation {
    private String name;

    public static Affiliation from(AffiliationDto dto) {
      return Affiliation.builder().name(dto.getName()).build();
    }
  }
}
