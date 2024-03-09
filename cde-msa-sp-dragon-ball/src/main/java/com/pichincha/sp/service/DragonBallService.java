package com.pichincha.sp.service;

import com.pichincha.services.server.models.CharactersResponse;
import reactor.core.publisher.Mono;

public interface DragonBallService {

  Mono<CharactersResponse> getAllCharacters();
}
