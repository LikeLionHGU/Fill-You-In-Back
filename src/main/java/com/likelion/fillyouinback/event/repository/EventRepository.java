package com.likelion.fillyouinback.event.repository;

import com.likelion.fillyouinback.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {}
