package com.trafiklab.utils;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class RestClient<T> {
    private final RestTemplate restTemplate;

    @SneakyThrows
    @Retryable(retryFor = Exception.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    public Optional<T> executeGetApiCallRetryable(String url, Class<T> resClass) {
        ResponseEntity<T> response = null;
        try {
            log.info("Executing api call for: {}", url);
            // create headers
            HttpHeaders headers = new HttpHeaders();
            // set `accept` header
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            // build the request
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            // use exchange method for HTTP call
            try {
                response = this.restTemplate.exchange(url, HttpMethod.GET, entity, resClass);
            } catch (HttpStatusCodeException e) {
                throw new Exception(String.format("Api call can not be executed, caused by: %s", e.getCause()));
            }
            if (response.getStatusCode() == HttpStatus.OK) {
                return Optional.ofNullable(response.getBody());
            } else {
                throw new Exception(String.format("Api call executed with an error: {}, caused by: %s",response.getStatusCode(), response.getBody()));
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new Exception(String.format("Api call can not be executed, caused by: %s", e.getCause()));
        }
    }

}
