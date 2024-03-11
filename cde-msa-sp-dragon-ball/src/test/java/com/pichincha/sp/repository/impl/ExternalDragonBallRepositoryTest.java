package com.pichincha.sp.repository.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.pichincha.sp.configuration.ApplicationProperties;
import com.pichincha.sp.helper.ApiHelper;
import com.pichincha.sp.service.dto.DragonBallExternalApiResponse;
import com.pichincha.sp.service.dto.DragonBallExternalCharacterResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {ExternalDragonBallRepositoryImpl.class},
    initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties(value = ApplicationProperties.class)
class ExternalDragonBallRepositoryTest {

  @Autowired
  ExternalDragonBallRepositoryImpl externalDragonBallRepository;
  @MockBean
  ApiHelper apiHelper;

  @Test
  void shouldGetAllCharacters() {
    when(apiHelper.doGet(anyString(), any(HttpHeaders.class), eq(DragonBallExternalApiResponse.class)))
        .thenReturn(Mono.just(new DragonBallExternalApiResponse()));
    StepVerifier.create(externalDragonBallRepository.getAllCharacters())
        .expectNextMatches(result -> result != null)
        .expectComplete()
        .verify();
  }

  @Test
  void shouldGetSpecificCharacter() {
    when(apiHelper.doGet(anyString(), any(HttpHeaders.class), eq(DragonBallExternalCharacterResponse.class)))
        .thenReturn(Mono.just(new DragonBallExternalCharacterResponse()));
    StepVerifier.create(externalDragonBallRepository.getSpecificCharacter(RandomStringUtils.randomNumeric(2)))
        .expectNextMatches(result -> result != null)
        .expectComplete()
        .verify();
  }
}
