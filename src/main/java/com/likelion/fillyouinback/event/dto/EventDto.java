package com.likelion.fillyouinback.event.dto;

import com.likelion.fillyouinback.event.controller.request.CreateEventRequest;
import com.likelion.fillyouinback.event.controller.request.UpdateEventRequest;
import com.likelion.fillyouinback.event.domain.Event;
import com.likelion.fillyouinback.folder.dto.FolderDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class EventDto {
  private Long id;

  private String title;

  private LocalDate startDate;

  private LocalDate endDate;

  private String mainText;

  private String imageUrl;

  private FolderDto folderDto;

  private LocalDateTime createdDate;

  public static EventDto from(CreateEventRequest request) {
    return EventDto.builder()
        .title(request.getTitle())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .mainText(request.getMainText())
        .folderDto(FolderDto.builder().id(request.getFolderId()).build())
        .build();
  }

  public static EventDto from(Event event) {
    return EventDto.builder()
        .id(event.getId())
        .title(event.getTitle())
        .startDate(event.getStartDate())
        .endDate(event.getEndDate())
        .mainText(event.getMainText())
        .imageUrl(event.getImageUrl())
        .createdDate(event.getCreatedDate())
        .build();
  }

  public static EventDto from(Long id, UpdateEventRequest request) {
    return EventDto.builder()
        .id(id)
        .title(request.getTitle())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .mainText(request.getMainText())
        .build();
  }
}
