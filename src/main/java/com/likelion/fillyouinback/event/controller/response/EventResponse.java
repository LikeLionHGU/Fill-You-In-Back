package com.likelion.fillyouinback.event.controller.response;

import com.likelion.fillyouinback.event.dto.EventDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class EventResponse {
  private Long id;

  private String title;

  private LocalDate startDate;

  private LocalDate endDate;

  private String mainText;

  public static EventResponse from(EventDto dto) {
    return EventResponse.builder()
        .id(dto.getId())
        .title(dto.getTitle())
        .startDate(dto.getStartDate())
        .endDate(dto.getEndDate())
        .mainText(dto.getMainText())
        .build();
  }
}
