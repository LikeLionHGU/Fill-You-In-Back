package com.likelion.fillyouinback.affiliation.service;

import com.likelion.fillyouinback.affiliation.dto.AffiliationDto;
import com.likelion.fillyouinback.affiliation.repository.AffiliationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AffiliationService {
  private final AffiliationRepository affiliationRepository;

  public List<AffiliationDto> getAffiliationList() {
    return affiliationRepository.findAll().stream().map(AffiliationDto::from).toList();
  }
}
