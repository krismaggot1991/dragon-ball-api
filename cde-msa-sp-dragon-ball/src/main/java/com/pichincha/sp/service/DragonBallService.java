package com.pichincha.sp.service;

import com.pichincha.services.server.models.CharactersResponse;
import com.pichincha.services.server.models.SpecificCharacterResponse;
import reactor.core.publisher.Mono;

public interface DragonBallService {

  Mono<CharactersResponse> getAllCharacters();

  Mono<SpecificCharacterResponse> getSpecificCharacter(String id);
}
