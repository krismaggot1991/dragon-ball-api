package com.pichincha.sp.service.impl;

import static lombok.AccessLevel.PRIVATE;

import com.pichincha.log.annotation.Loggable;
import com.pichincha.log.constants.Level;
import com.pichincha.services.server.models.CharactersResponse;
import com.pichincha.services.server.models.SpecificCharacterResponse;
import com.pichincha.sp.domain.enums.StatusCodeEnum;
import com.pichincha.sp.repository.ExternalDragonBallRepository;
import com.pichincha.sp.service.DragonBallService;
import com.pichincha.sp.service.mapper.DragonBallMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DragonBallServiceImpl implements DragonBallService {

  ExternalDragonBallRepository externalDragonBallRepository;
  DragonBallMapper dragonBallMapper;

  @Override
  @Loggable(level = Level.LEVEL_002)
  public Mono<CharactersResponse> getAllCharacters() {
    return externalDragonBallRepository.getAllCharacters()
        .map(dragonBallExternalApiResponse -> {
          CharactersResponse charactersResponse = dragonBallMapper.toCharactersResponse(dragonBallExternalApiResponse);
          charactersResponse.setCode(StatusCodeEnum.OK.getCode());
          charactersResponse.setMessage(StatusCodeEnum.OK.getMessage());
          return charactersResponse;
        });
  }

  @Override
  @Loggable(level = Level.LEVEL_002)
  public Mono<SpecificCharacterResponse> getSpecificCharacter(String id) {
    return externalDragonBallRepository.getSpecificCharacter(id)
        .map(dragonBallExternalCharacterResponse -> {
          SpecificCharacterResponse specificCharacterResponse = new SpecificCharacterResponse();
          specificCharacterResponse.setCode(StatusCodeEnum.OK.getCode());
          specificCharacterResponse.setMessage(StatusCodeEnum.OK.getMessage());
          specificCharacterResponse.setData(dragonBallMapper.toSpecificCharactersDataResponse(dragonBallExternalCharacterResponse));
          return specificCharacterResponse;
        }).onErrorResume(throwable -> Mono.just(new SpecificCharacterResponse()
            .code(StatusCodeEnum.NOT_FOUND.getCode())
            .message(StatusCodeEnum.NOT_FOUND.getMessage())));
  }
}
