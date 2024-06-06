package com.likelion.fillyouinback.event.controller;

import com.likelion.fillyouinback.event.controller.request.CreateEventRequest;
import com.likelion.fillyouinback.event.controller.request.UpdateEventRequest;
import com.likelion.fillyouinback.event.controller.response.EventListResponse;
import com.likelion.fillyouinback.event.controller.response.EventResponse;
import com.likelion.fillyouinback.event.dto.EventDto;
import com.likelion.fillyouinback.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EventController {
  private final EventService eventService;

  @PostMapping("/api/fillyouin/events")
  public ResponseEntity<Void> createEvent(@RequestBody CreateEventRequest request) {
    eventService.createEvent(EventDto.from(request));
    return ResponseEntity.ok().build();
  }

  @GetMapping("/api/fillyouin/folders/{folderId}/events")
  public ResponseEntity<EventListResponse> getEventList(@PathVariable Long folderId) {
    return ResponseEntity.ok(EventListResponse.from(eventService.getEventList(folderId)));
  }

  @GetMapping("/api/fillyouin/events/{id}")
  public ResponseEntity<EventResponse> getEvent(@PathVariable Long id) {
    return ResponseEntity.ok(EventResponse.from(eventService.getEvent(id)));
  }

  @PatchMapping("/api/fillyouin/events/{id}")
  public ResponseEntity<Void> updateEvent(
      @PathVariable Long id, @RequestBody UpdateEventRequest request) {
    eventService.updateEvent(EventDto.from(id, request));
    return ResponseEntity.ok().build();
  }
}
