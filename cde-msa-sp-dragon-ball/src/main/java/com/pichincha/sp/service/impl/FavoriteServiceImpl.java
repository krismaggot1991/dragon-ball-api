package com.pichincha.sp.service.impl;

import static lombok.AccessLevel.PRIVATE;

import com.pichincha.services.server.models.FavoriteCharacterResponse;
import com.pichincha.services.server.models.FavoritesRequest;
import com.pichincha.services.server.models.GenericResponse;
import com.pichincha.services.server.models.SpecificCharacterResponse;
import com.pichincha.sp.domain.enums.StatusCodeEnum;
import com.pichincha.sp.domain.jpa.FavoriteEntity;
import com.pichincha.sp.repository.FavoriteRepository;
import com.pichincha.sp.repository.UserRepository;
import com.pichincha.sp.service.DragonBallService;
import com.pichincha.sp.service.FavoriteService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class FavoriteServiceImpl implements FavoriteService {

  UserRepository userRepository;
  DragonBallService dragonBallService;
  FavoriteRepository favoriteRepository;

  @Override
  public Mono<GenericResponse> registerFavoriteCharacter(FavoritesRequest favoritesRequest) {
    return Mono.zip(userRepository.findByUserName(favoritesRequest.getUsername()).collectList(),
            dragonBallService.getSpecificCharacter(favoritesRequest.getCharacterId()))
        .filter(tuple -> !tuple.getT1().isEmpty() && tuple.getT2().getCode().equals(StatusCodeEnum.OK.getCode()))
        .flatMap(tuple -> favoriteRepository.findByUserNameAndCharacterId(favoritesRequest.getUsername(), favoritesRequest.getCharacterId())
            .collectList()
            .filter(List::isEmpty)
            .flatMap(favoriteEntities -> favoriteRepository.save(FavoriteEntity.builder()
                    .userName(favoritesRequest.getUsername())
                    .characterId(favoritesRequest.getCharacterId())
                    .createdOn(LocalDateTime.now())
                    .build())
                .flatMap(favoriteEntity -> Mono.just(new GenericResponse()
                    .code(StatusCodeEnum.OK.getCode())
                    .message(StatusCodeEnum.OK.getMessage())))).switchIfEmpty(Mono.defer(() -> Mono.just(new GenericResponse()
                .code(StatusCodeEnum.FAVORITE_CHARACTER_ALREADY_REGISTERED.getCode())
                .message(StatusCodeEnum.FAVORITE_CHARACTER_ALREADY_REGISTERED.getMessage())))))
        .switchIfEmpty(Mono.defer(() -> Mono.just(new GenericResponse()
            .code(StatusCodeEnum.BAD_REQUEST_REGISTER_FAVORITE_CHARACTER.getCode())
            .message(StatusCodeEnum.BAD_REQUEST_REGISTER_FAVORITE_CHARACTER.getMessage()))));

  }

  @Override
  public Mono<FavoriteCharacterResponse> getFavoriteFromUser(String username) {
    return favoriteRepository.findByUserName(username)
        .flatMap(favoriteEntity -> dragonBallService.getSpecificCharacter(favoriteEntity.getCharacterId())
            .map(SpecificCharacterResponse::getData))
        .collectList()
        .map(data -> new FavoriteCharacterResponse()
            .code(StatusCodeEnum.OK.getCode())
            .data(data)
            .message(StatusCodeEnum.OK.getMessage()));
  }

  @Override
  public Mono<GenericResponse> deleteFavoriteCharacterFromUser(String username, String characterId) {
    return favoriteRepository.findByUserNameAndCharacterId(username, characterId)
        .flatMap(favoriteEntities -> favoriteRepository.delete(favoriteEntities)
            .flatMap(unused -> Mono.just(new GenericResponse()
                .code(StatusCodeEnum.OK.getCode())
                .message(StatusCodeEnum.OK.getMessage()))))
        .collectList()
        .map(data -> new GenericResponse()
            .code(StatusCodeEnum.OK.getCode())
            .message(StatusCodeEnum.OK.getMessage()));
  }

}
