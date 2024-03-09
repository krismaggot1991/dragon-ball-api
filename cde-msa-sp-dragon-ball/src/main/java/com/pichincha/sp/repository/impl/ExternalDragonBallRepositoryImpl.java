package com.pichincha.sp.repository.impl;

import static lombok.AccessLevel.PRIVATE;

import com.pichincha.sp.configuration.ApplicationProperties;
import com.pichincha.sp.helper.ApiHelper;
import com.pichincha.sp.repository.ExternalDragonBallRepository;
import com.pichincha.sp.service.dto.DragonBallExternalApiResponse;
import com.pichincha.sp.service.dto.DragonBallExternalCharacterResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ExternalDragonBallRepositoryImpl implements ExternalDragonBallRepository {

  ApiHelper apiHelper;
  ApplicationProperties applicationProperties;

  @Override
  public Mono<DragonBallExternalApiResponse> getAllCharacters() {
    String url = applicationProperties.getServices().getDragonBall().getBasePath()
        .concat(applicationProperties.getServices().getDragonBall().getAllCharacters().getPath());
    return apiHelper.doGet(url, new HttpHeaders(), DragonBallExternalApiResponse.class);
  }

  @Override
  public Mono<DragonBallExternalCharacterResponse> getSpecificCharacter(String id) {
    String url = applicationProperties.getServices().getDragonBall().getBasePath()
        .concat(String.format(applicationProperties.getServices().getDragonBall().getSpecificCharacter().getPath(), id));
    return apiHelper.doGet(url, new HttpHeaders(), DragonBallExternalCharacterResponse.class);
  }
}
