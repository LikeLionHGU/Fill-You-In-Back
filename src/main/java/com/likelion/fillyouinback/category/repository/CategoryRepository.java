package com.likelion.fillyouinback.category.repository;

import com.likelion.fillyouinback.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByMemberId(Long memberId);
}
