package com.likelion.fillyouinback.event.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CreateEventRequest {
    private Long categoryId;

    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String mainText;
}
