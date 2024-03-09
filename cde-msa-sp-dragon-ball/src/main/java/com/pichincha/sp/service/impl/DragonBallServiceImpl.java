package com.pichincha.sp.service.impl;

import static lombok.AccessLevel.PRIVATE;

import com.pichincha.services.server.models.CharactersResponse;
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
  public Mono<CharactersResponse> getAllCharacters() {
    return externalDragonBallRepository.getAllCharacters()
        .map(dragonBallExternalApiResponse -> {
          CharactersResponse charactersResponse = dragonBallMapper.toCharactersResponse(dragonBallExternalApiResponse);
          charactersResponse.setCode(StatusCodeEnum.OK.getCode());
          charactersResponse.setMessage(StatusCodeEnum.OK.getMessage());
          return charactersResponse;
        });
  }
}
