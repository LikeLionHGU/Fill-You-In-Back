package com.likelion.fillyouinback.event.controller.response;

import com.likelion.fillyouinback.event.dto.EventDto;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Getter
public class EventListResponse {

  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private List<Event> events;

  public static EventListResponse from(List<EventDto> eventList) {
    return EventListResponse.builder().events(eventList.stream().map(Event::from).toList()).build();
  }

  @Builder
  @Getter
  private static class Event {
    private Long id;
    private String title;
    private String createdDate;
    private String mainText;
    private String imageUrl;

    public static Event from(EventDto eventDto) {
      return Event.builder()
          .id(eventDto.getId())
          .title(eventDto.getTitle())
          .createdDate(eventDto.getCreatedDate().format(DATE_TIME_FORMATTER))
          .mainText(eventDto.getMainText())
          .imageUrl(eventDto.getImageUrl())
          .build();
    }
  }
}
