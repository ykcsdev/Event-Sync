package com.ibm.eventsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.eventsync.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {}
