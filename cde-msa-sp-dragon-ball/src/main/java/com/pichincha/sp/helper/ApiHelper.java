package com.pichincha.sp.helper;

import static lombok.AccessLevel.PRIVATE;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ApiHelper {

  WebClient client;

  public <T> Mono<T> doPost(String url, HttpHeaders headers, Object request, Class<T> typeReference) {
    return client.post()
        .uri(url)
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .bodyValue(request)
        .retrieve()
        .bodyToMono(typeReference);
  }

  public <T> Mono<T> doGet(String url, HttpHeaders headers, Class<T> typeReference) {
    return client.get()
        .uri(url)
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .retrieve()
        .bodyToMono(typeReference);
  }
}
