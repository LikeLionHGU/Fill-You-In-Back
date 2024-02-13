package com.likelion.fillyouinback.department.controller;

import com.likelion.fillyouinback.department.controller.response.DepartmentListResponse;
import com.likelion.fillyouinback.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fillyouin/departments")
public class DepartmentController {
  private final DepartmentService departmentService;

  @GetMapping
  public ResponseEntity<DepartmentListResponse> getDepartmentList() {
    return ResponseEntity.ok(new DepartmentListResponse(departmentService.getDepartmentList()));
  }
}
