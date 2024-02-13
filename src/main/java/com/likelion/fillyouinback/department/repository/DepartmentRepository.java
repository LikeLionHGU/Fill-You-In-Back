package com.likelion.fillyouinback.department.repository;

import com.likelion.fillyouinback.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}
