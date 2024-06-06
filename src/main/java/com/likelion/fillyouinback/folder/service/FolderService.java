package com.likelion.fillyouinback.folder.service;

import com.likelion.fillyouinback.base.exception.NotFoundException;
import com.likelion.fillyouinback.category.repository.CategoryRepository;
import com.likelion.fillyouinback.event.repository.EventRepository;
import com.likelion.fillyouinback.folder.domain.Folder;
import com.likelion.fillyouinback.folder.dto.FolderDto;
import com.likelion.fillyouinback.folder.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FolderService {
  private final FolderRepository folderRepository;
  private final CategoryRepository categoryRepository;
  private final EventRepository eventRepository;

  public void addFolder(FolderDto dto) {
    folderRepository.save(
        Folder.from(
            dto,
            categoryRepository
                .findById(dto.getCategoryDto().getId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 카테고리입니다."))));
  }

  public List<FolderDto> getFolderList(Long categoryId) {
    return folderRepository.findByCategoryId(categoryId).stream().map(FolderDto::from).toList();
  }

  public void deleteFolder(Long id) {
    eventRepository.deleteByFolderId(id);
    folderRepository.deleteById(id);
  }

  public void updateFolder(FolderDto dto) {
    Folder folder =
        folderRepository.findById(dto.getId()).orElseThrow(() -> new NotFoundException("존재하지 않는 폴더입니다."));
    folder.update(dto);
  }
}
