package com.likelion.fillyouinback.field.service;

import com.likelion.fillyouinback.field.dto.FieldDto;
import java.util.List;

import com.likelion.fillyouinback.field.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldService {
  private final FieldRepository fieldRepository;

  public List<FieldDto> getFieldList() {
    return fieldRepository.findAll().stream().map(FieldDto::from).toList();
  }
}
