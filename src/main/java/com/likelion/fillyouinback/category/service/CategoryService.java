package com.likelion.fillyouinback.category.service;

import com.likelion.fillyouinback.base.exception.NotFoundException;
import com.likelion.fillyouinback.category.domain.Category;
import com.likelion.fillyouinback.category.dto.CategoryDto;
import com.likelion.fillyouinback.category.repository.CategoryRepository;
import com.likelion.fillyouinback.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final MemberRepository memberRepository;

  public void addCategory(CategoryDto dto) {
    categoryRepository.save(
        Category.from(
            memberRepository
                .findById(dto.getMemberId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다.")),
            dto));
  }

  public List<CategoryDto> getCategoryList(Long memberId) {
    return categoryRepository.findByMemberId(memberId).stream()
        .map(CategoryDto::from)
        .collect(Collectors.toList());
  }
}
