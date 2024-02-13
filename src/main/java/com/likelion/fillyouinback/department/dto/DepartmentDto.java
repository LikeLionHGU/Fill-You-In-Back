package com.likelion.fillyouinback.department.dto;

import com.likelion.fillyouinback.department.domain.Department;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DepartmentDto {
  private String name;

  public static DepartmentDto from(Department department) {
    return DepartmentDto.builder().name(department.getName()).build();
  }
}
