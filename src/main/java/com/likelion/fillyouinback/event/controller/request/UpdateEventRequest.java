package com.likelion.fillyouinback.event.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class UpdateEventRequest {
    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private String mainText;
}
