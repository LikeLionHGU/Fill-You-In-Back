package com.likelion.fillyouinback.folder.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.category.domain.Category;
import com.likelion.fillyouinback.folder.dto.FolderDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Folder extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public static Folder from(FolderDto dto, Category category) {
    return Folder.builder().name(dto.getName()).category(category).build();
  }
}
