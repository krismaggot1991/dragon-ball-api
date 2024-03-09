package com.pichincha.sp.configuration;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableWebFlux
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class WebFluxConfiguration implements WebFluxConfigurer {

  ObjectMapper objectMapper;
  ApplicationProperties properties;

  @Override
  public void configureHttpMessageCodecs(ServerCodecConfigurer configure) {
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    configure.defaultCodecs()
        .maxInMemorySize((int) properties.getMaxInMemorySize().toBytes());
    configure.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
    configure.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
  }

  @Bean
  public WebClient webClient() {
    HttpClient httpClient = HttpClient.create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
        .doOnConnected(connection -> {
          connection.addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout(), TimeUnit.MILLISECONDS));
          connection.addHandlerLast(new WriteTimeoutHandler(properties.getReadTimeout(), TimeUnit.MILLISECONDS));
        });
    return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
  }
}
