package com.ibm.eventsync.config;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class EnvironmentService {

	private String huggingFaceToken;
	private String presentation;

	public EnvironmentService() {
		Dotenv dotenv = null;
		try {
			dotenv = Dotenv.configure().filename("eventsync.env").ignoreIfMissing().load();
			log.info("Loaded eventsync.env successfully.");
		} catch (Exception e) {
			log.warn("Unable to load eventsync.env file: {}", e.getMessage());
		}

		this.huggingFaceToken = getValue(dotenv, "hf_token", "HF_TOKEN");
		this.presentation = getValue(dotenv, "baeldung.presentation", "BAELDUNG_PRESENTATION");

		log.info("EnvironmentService initialized. hf_token: {}, presentation: {}",
				huggingFaceToken != null ? "SET" : "NOT SET", presentation);
	}

	private String getValue(Dotenv dotenv, String fileKey, String envKey) {
		if (dotenv != null && dotenv.get(fileKey) != null)
			return dotenv.get(fileKey);
		return System.getenv(envKey);
	}
}
