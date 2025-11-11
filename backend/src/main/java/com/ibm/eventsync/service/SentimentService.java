package com.ibm.eventsync.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.eventsync.config.EnvironmentService;
import com.ibm.eventsync.model.Feedback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SentimentService {
	@Autowired
	private EnvironmentService env;
	private String NEUTRAL = "neutral";

	private static final String MODEL_URL = "https://router.huggingface.co/hf-inference/models/cardiffnlp/twitter-roberta-base-sentiment-latest";

	public Feedback analyze(Feedback Obj) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(env.getHuggingFaceToken());
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			Map<String, String> payload = Collections.singletonMap("inputs", Obj.getText());
			Obj.setSentiment(NEUTRAL);
			Obj.setSentiment_score(-1.0);

			HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(MODEL_URL, request, String.class);

			if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
				log.error("Unexpected response: " + response);
				return Obj;
			}

			JSONArray inner = new JSONArray(response.getBody()).getJSONArray(0);
			double bestScore = -1.0;
			String bestLabel = NEUTRAL;

			for (int i = 0; i < inner.length(); i++) {
				JSONObject obj = inner.getJSONObject(i);
				double currentScore = obj.getBigDecimal("score").setScale(2, RoundingMode.HALF_UP).doubleValue();
				String currentLabel = obj.getString("label").toLowerCase();
				if (currentScore > bestScore) {
					bestScore = currentScore;
					bestLabel = currentLabel;
				}
			}
			Obj.setSentiment_score(bestScore);
			Obj.setSentiment(bestLabel);
			return Obj;

		} catch (Exception e) {
			log.error(e.toString());
			return Obj;
		}
	}
}
