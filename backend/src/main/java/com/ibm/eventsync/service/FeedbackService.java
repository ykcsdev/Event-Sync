package com.ibm.eventsync.service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.eventsync.model.Event;
import com.ibm.eventsync.model.Feedback;
import com.ibm.eventsync.repository.FeedbackRepository;

@Service
public class FeedbackService {
	@Autowired
	private FeedbackRepository repo;
	@Autowired
	private EventService eventService;
	@Autowired
	private SentimentService sentimentService;

	public Feedback add(Long eventId, String text) {
		Event event = eventService.get(eventId);
		Feedback fb = new Feedback();
		fb.setEvent(event);
		fb.setText(text);
		fb.setTimestamp(LocalDateTime.now());
		fb = sentimentService.analyze(fb);
		return repo.save(fb);
	}

	public Map<String, Object> summary(Long eventId) {
		Event event = eventService.get(eventId);
		List<Feedback> list = repo.findByEvent(event);

		Map<String, Long> sentimentCounts = list.stream().filter(f -> f.getSentiment() != null).collect(Collectors.groupingBy(Feedback::getSentiment, Collectors.counting()));

		long total = list.stream().filter(f -> f.getSentiment_score() != null).count();

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("total", total);
		response.putAll(sentimentCounts);
		return response;
	}

}
