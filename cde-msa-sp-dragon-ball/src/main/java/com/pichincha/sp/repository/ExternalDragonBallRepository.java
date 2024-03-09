package com.pichincha.sp.repository;

import com.pichincha.sp.service.dto.DragonBallExternalApiResponse;
import reactor.core.publisher.Mono;

public interface ExternalDragonBallRepository {

  Mono<DragonBallExternalApiResponse> getAllCharacters();
}
