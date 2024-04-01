package com.example.capstone2.Repository;

import com.example.capstone2.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findEventByEventId(Integer id);

    List<Event> findEventsByEventDateBetween(LocalDate start, LocalDate end);

    List<Event> findEventsByEventDateAfter(LocalDate date);
}
