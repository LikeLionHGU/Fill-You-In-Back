package com.likelion.fillyouinback.event.controller;

import com.likelion.fillyouinback.event.controller.request.CreateEventRequest;
import com.likelion.fillyouinback.event.dto.EventDto;
import com.likelion.fillyouinback.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {
  private final EventService eventService;

  @PostMapping("/api/fillyouin/events")
  public ResponseEntity<Void> createEvent(@RequestBody CreateEventRequest request) {
    eventService.createEvent(EventDto.from(request));
    return ResponseEntity.ok().build();
  }
}
