package com.likelion.fillyouinback.event.domain;

import com.likelion.fillyouinback.base.domain.BaseTime;
import com.likelion.fillyouinback.event.dto.EventDto;
import com.likelion.fillyouinback.folder.domain.Folder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Event extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false, length = 50)
  private String title;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;

  @Column(name = "main_text", columnDefinition = "TEXT")
  private String mainText;

  @Column(name = "image_url", length = 300)
  private String imageUrl;

  @ManyToOne
  @JoinColumn(name = "folder_id")
  private Folder folder;

  public static Event from(Folder folder, EventDto dto) {
    return Event.builder()
        .folder(folder)
        .title(dto.getTitle())
        .startDate(dto.getStartDate())
        .endDate(dto.getEndDate())
        .mainText(dto.getMainText())
        .imageUrl(dto.getImageUrl())
        .build();
  }

  public void update(EventDto dto) {
    this.title = dto.getTitle();
    this.startDate = dto.getStartDate();
    this.endDate = dto.getEndDate();
    this.mainText = dto.getMainText();
    this.imageUrl = dto.getImageUrl();
  }
}
