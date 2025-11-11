package com.ibm.eventsync.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.eventsync.model.Event;
import com.ibm.eventsync.model.Feedback;
import com.ibm.eventsync.service.EventService;
import com.ibm.eventsync.service.FeedbackService;

@RestController
@RequestMapping("/eventsAPI")
public class EventController {

	@Autowired
    private EventService eventService;
	@Autowired
    private FeedbackService feedbackService;
	
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.create(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.all();
    }
    @PostMapping("/{eventId}/feedback")
    public Feedback addFeedback(@PathVariable Long eventId, @RequestBody Map<String, String> body) {
        return feedbackService.add(eventId, body.get("text"));
    }

    @GetMapping("/{eventId}/summary")
    public Map<String, Object> getFeedbackSummary(@PathVariable Long eventId) {
        return feedbackService.summary(eventId);
    }
}
