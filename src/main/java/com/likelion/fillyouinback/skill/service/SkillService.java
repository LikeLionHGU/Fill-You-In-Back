package com.likelion.fillyouinback.skill.service;

import com.likelion.fillyouinback.skill.dto.SkillDto;
import com.likelion.fillyouinback.skill.repository.SkillRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {
  private final SkillRepository skillRepository;

  public List<SkillDto> getSkillList() {
    return skillRepository.findAll().stream().map(SkillDto::from).toList();
  }
}
