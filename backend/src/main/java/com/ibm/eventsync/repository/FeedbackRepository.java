package com.ibm.eventsync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.eventsync.model.Event;
import com.ibm.eventsync.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByEvent(Event event);
}
