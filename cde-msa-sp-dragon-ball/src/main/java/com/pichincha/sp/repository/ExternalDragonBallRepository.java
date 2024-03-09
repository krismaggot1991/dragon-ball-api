package com.pichincha.sp.repository;

import com.pichincha.sp.service.dto.DragonBallExternalApiResponse;
import com.pichincha.sp.service.dto.DragonBallExternalCharacterResponse;
import reactor.core.publisher.Mono;

public interface ExternalDragonBallRepository {

  Mono<DragonBallExternalApiResponse> getAllCharacters();

  Mono<DragonBallExternalCharacterResponse> getSpecificCharacter(String id);
}
