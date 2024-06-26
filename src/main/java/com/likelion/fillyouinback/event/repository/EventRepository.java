package com.likelion.fillyouinback.event.repository;

import com.likelion.fillyouinback.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
  @Query("SELECT e FROM Event e WHERE e.folder.id = :folderId")
  List<Event> findAllByFolderId(Long folderId);

  @Modifying
  @Query("DELETE FROM Event e WHERE e.folder.category.id = :categoryId")
  void deleteByCategoryId(Long categoryId);

  @Modifying
  @Query("DELETE FROM Event e WHERE e.folder.id = :id")
  void deleteByFolderId(Long id);
}
