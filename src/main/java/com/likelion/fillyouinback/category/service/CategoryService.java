package com.likelion.fillyouinback.category.service;

import com.likelion.fillyouinback.base.exception.NotFoundException;
import com.likelion.fillyouinback.category.domain.Category;
import com.likelion.fillyouinback.category.dto.CategoryDto;
import com.likelion.fillyouinback.category.repository.CategoryRepository;
import com.likelion.fillyouinback.event.repository.EventRepository;
import com.likelion.fillyouinback.folder.repository.FolderRepository;
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
  private final FolderRepository folderRepository;
  private final EventRepository eventRepository;

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

    public void updateCategory(CategoryDto dto) {
        Category category = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 카테고리입니다."));
        category.update(dto);
    }

  public void deleteCategory(Long categoryId) {
    eventRepository.deleteByCategoryId(categoryId);
    folderRepository.deleteByCategoryId(categoryId);
    categoryRepository.deleteById(categoryId);
  }
}
