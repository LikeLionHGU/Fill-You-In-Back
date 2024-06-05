package com.likelion.fillyouinback.event.dto;

import com.likelion.fillyouinback.category.dto.CategoryDto;
import com.likelion.fillyouinback.event.controller.request.CreateEventRequest;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class EventDto {
  private Long id;

  private String title;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private String mainText;

  private CategoryDto categoryDto;

  public static EventDto from(CreateEventRequest request) {
    return EventDto.builder()
        .id(request.getId())
        .title(request.getTitle())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .mainText(request.getMainText())
        .categoryDto(CategoryDto.builder().id(request.getCategoryId()).build())
        .build();
  }
}
