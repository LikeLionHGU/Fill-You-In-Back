package com.likelion.fillyouinback.event.service;

import com.likelion.fillyouinback.category.domain.Category;
import com.likelion.fillyouinback.category.repository.CategoryRepository;
import com.likelion.fillyouinback.event.domain.Event;
import com.likelion.fillyouinback.event.dto.EventDto;
import com.likelion.fillyouinback.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    public void createEvent(EventDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryDto().getId()).orElseThrow();
        eventRepository.save(Event.from(category,dto));
    }
}
