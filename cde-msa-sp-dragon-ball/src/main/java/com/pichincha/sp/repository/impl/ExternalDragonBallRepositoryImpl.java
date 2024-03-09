package com.pichincha.sp.repository.impl;

import static lombok.AccessLevel.PRIVATE;

import com.pichincha.sp.helper.ApiHelper;
import com.pichincha.sp.repository.ExternalDragonBallRepository;
import com.pichincha.sp.service.dto.DragonBallExternalApiResponse;
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

  @Override
  public Mono<DragonBallExternalApiResponse> getAllCharacters() {
    String url = "https://dragonball-api.com/api/characters?limit=100";
    return apiHelper.doGet(url, new HttpHeaders(), DragonBallExternalApiResponse.class);
  }
}
