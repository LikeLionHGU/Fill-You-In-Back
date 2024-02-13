package com.likelion.fillyouinback.department.service;

import com.likelion.fillyouinback.department.dto.DepartmentDto;
import com.likelion.fillyouinback.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
  private final DepartmentRepository departmentRepository;

  public List<DepartmentDto> getDepartmentList() {
    return departmentRepository.findAll().stream().map(DepartmentDto::from).toList();
  }
}
