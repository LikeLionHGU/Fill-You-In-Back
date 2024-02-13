package com.likelion.fillyouinback.department.controller.response;

import com.likelion.fillyouinback.department.dto.DepartmentDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DepartmentListResponse {
  private final List<Department> departments;

  public DepartmentListResponse(List<DepartmentDto> dtos) {
    this.departments = dtos.stream().map(Department::from).toList();
  }

  @Builder
  @Getter
  private static class Department {
    private String name;

    public static Department from(DepartmentDto dto) {
      return Department.builder().name(dto.getName()).build();
    }
  }
}
