package com.likelion.fillyouinback.event.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.category.domain.Category;
import com.likelion.fillyouinback.event.dto.EventDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Event extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false, length = 50)
  private String title;

  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDateTime endDate;

  @Column(name = "main_text", columnDefinition = "TEXT")
  private String mainText;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public static Event from(Category category, EventDto dto) {
    return Event.builder()
        .category(category)
        .title(dto.getTitle())
        .startDate(dto.getStartDate())
        .endDate(dto.getEndDate())
        .mainText(dto.getMainText())
        .build();
  }
}
