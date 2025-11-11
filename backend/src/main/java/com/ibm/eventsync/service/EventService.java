package com.ibm.eventsync.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.eventsync.model.Event;
import com.ibm.eventsync.repository.EventRepository;

@Service
public class EventService {
	@Autowired
    private EventRepository repo;
	
    public Event create(Event e) { return repo.save(e); }
    public List<Event> all() { return repo.findAll(); }
    public Event get(Long id) { return repo.findById(id).orElseThrow(); }
}
