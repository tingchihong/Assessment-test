package com.assessment.test.service.impl;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.assessment.test.dto.TodoDto;
import com.assessment.test.service.ThirdPartyService;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

	private static final String MOCK_API_BASE_URL = "https://jsonplaceholder.typicode.com";
	private static final String USER_AGENT = "Spring 5 WebClient";
	private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(30);
	private static final Logger logger = LoggerFactory.getLogger(ThirdPartyServiceImpl.class);

	private final WebClient webClient;

	@Autowired
	public ThirdPartyServiceImpl() {
		this.webClient = WebClient.builder().baseUrl(MOCK_API_BASE_URL)
				.defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT).filter(logRequest()).build();
	}

	public TodoDto[] getUserList() {
		return webClient.get().uri("/todos").retrieve().bodyToMono(TodoDto[].class).block(REQUEST_TIMEOUT);
	}

	public TodoDto getUser(long id) {
		return webClient.get().uri("/todos/" + id).retrieve().bodyToMono(TodoDto.class).block(REQUEST_TIMEOUT);
	}

	private ExchangeFilterFunction logRequest() {
		return (clientRequest, next) -> {
			logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
			clientRequest.headers()
					.forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
			return next.exchange(clientRequest);
		};
	}
}
