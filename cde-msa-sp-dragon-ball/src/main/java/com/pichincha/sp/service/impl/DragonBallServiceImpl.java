package com.pichincha.sp.service.impl;

import static lombok.AccessLevel.PRIVATE;

import com.pichincha.services.server.models.CharactersResponse;
import com.pichincha.sp.service.DragonBallService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DragonBallServiceImpl implements DragonBallService {

  @Override
  public Mono<CharactersResponse> getAllCharacters() {
    CharactersResponse charactersResponse = new CharactersResponse();
    charactersResponse.setCode("200");
    charactersResponse.setMessage("OK");
    return Mono.just(charactersResponse);
  }
}
