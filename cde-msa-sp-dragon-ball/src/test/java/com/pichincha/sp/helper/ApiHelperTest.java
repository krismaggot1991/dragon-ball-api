package com.pichincha.sp.helper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ApiHelperTest {

  @InjectMocks
  ApiHelper helper;
  @Mock
  WebClient client;
  @Mock
  ResponseSpec responseSpec;
  @Mock
  RequestHeadersUriSpec requestHeadersUriSpec;

  @Test
  void doGetRetrievesBody() {
    String url = "https://www.example1.com";
    String expected = "result";
    when(client.get()).thenReturn(requestHeadersUriSpec);
    when(requestHeadersUriSpec.uri(url)).thenReturn(requestHeadersUriSpec);
    when(requestHeadersUriSpec.headers(any())).thenReturn(requestHeadersUriSpec);
    when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
    when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(expected));
    HttpHeaders headers = new HttpHeaders();
    StepVerifier.create(helper.doGet(url, headers, String.class))
        .expectNextMatches(result -> result.equalsIgnoreCase(expected))
        .verifyComplete();
  }


}
