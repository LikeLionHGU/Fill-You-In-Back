package com.likelion.fillyouinback.event.service;

import com.likelion.fillyouinback.event.domain.Event;
import com.likelion.fillyouinback.event.dto.EventDto;
import com.likelion.fillyouinback.event.repository.EventRepository;
import com.likelion.fillyouinback.folder.domain.Folder;
import com.likelion.fillyouinback.folder.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
  private final EventRepository eventRepository;
  private final FolderRepository folderRepository;

  public void createEvent(EventDto dto) {
    Folder folder = folderRepository.findById(dto.getFolderDto().getId()).orElseThrow();
    eventRepository.save(Event.from(folder, dto));
  }

  public List<EventDto> getEventList(Long folderId) {
    return eventRepository.findAllByFolderId(folderId).stream().map(EventDto::from).toList();
  }

  public EventDto getEvent(Long eventId) {
    return EventDto.from(eventRepository.findById(eventId).orElseThrow());
  }

  public void updateEvent(EventDto dto) {
    Event event = eventRepository.findById(dto.getId()).orElseThrow();
    event.update(dto);
  }
}
