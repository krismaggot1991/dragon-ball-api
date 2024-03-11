package com.pichincha.sp.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.pichincha.services.server.models.FavoriteCharacterResponse;
import com.pichincha.services.server.models.FavoritesRequest;
import com.pichincha.services.server.models.GenericResponse;
import com.pichincha.services.server.models.SpecificCharacterResponse;
import com.pichincha.services.server.models.SpecificCharactersDataResponse;
import com.pichincha.sp.domain.enums.StatusCodeEnum;
import com.pichincha.sp.domain.jpa.FavoriteEntity;
import com.pichincha.sp.domain.jpa.UserEntity;
import com.pichincha.sp.repository.FavoriteRepository;
import com.pichincha.sp.repository.UserRepository;
import com.pichincha.sp.service.DragonBallService;
import com.pichincha.sp.service.FavoriteService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {FavoriteServiceImpl.class},
    initializers = ConfigDataApplicationContextInitializer.class)
class FavoriteServiceTest {

  @Autowired
  FavoriteService favoriteService;
  @MockBean
  UserRepository userRepository;
  @MockBean
  DragonBallService dragonBallService;
  @MockBean
  FavoriteRepository favoriteRepository;

  @Test
  void givenValidRequestShouldRegisterFavoriteCharacter() {
    when(userRepository.findByUserName(anyString()))
        .thenReturn(Flux.just(UserEntity.builder()
            .id(1L)
            .build()));
    when(dragonBallService.getSpecificCharacter(anyString()))
        .thenReturn(Mono.just(new SpecificCharacterResponse()
            .code(StatusCodeEnum.OK.getCode())
        ));
    when(favoriteRepository.findByUserNameAndCharacterId(anyString(), anyString()))
        .thenReturn(Flux.empty());
    when(favoriteRepository.save(any()))
        .thenReturn(Mono.just(FavoriteEntity.builder().build()));
    Mono<GenericResponse> response = favoriteService.registerFavoriteCharacter(new FavoritesRequest()
        .username(RandomStringUtils.randomAlphabetic(10))
        .characterId(RandomStringUtils.randomAlphabetic(1))
    );
    assertEquals("Response should be OK", StatusCodeEnum.OK.getCode(), response.block().getCode());
    verify(userRepository, times(1)).findByUserName(anyString());
    verify(dragonBallService, times(1)).getSpecificCharacter(anyString());
    verify(favoriteRepository, times(1)).findByUserNameAndCharacterId(anyString(), anyString());
    verify(favoriteRepository, times(1)).save(any());
  }

  @Test
  void givenUsernameShouldGetFavoriteFromUser() {
    SpecificCharacterResponse specificCharacterResponse = new SpecificCharacterResponse();
    SpecificCharactersDataResponse specificCharactersDataResponse = new SpecificCharactersDataResponse();
    specificCharactersDataResponse.setId("1");
    specificCharacterResponse.setData(specificCharactersDataResponse);
    when(favoriteRepository.findByUserName(anyString()))
        .thenReturn(Flux.just(FavoriteEntity.builder()
            .characterId("15")
            .build()));
    when(dragonBallService.getSpecificCharacter(anyString()))
        .thenReturn(Mono.just(specificCharacterResponse));
    Mono<FavoriteCharacterResponse> response = favoriteService.getFavoriteFromUser(RandomStringUtils.randomAlphabetic(10));
    assertEquals("Response should be OK", StatusCodeEnum.OK.getCode(), response.block().getCode());
    verify(favoriteRepository, times(1)).findByUserName(anyString());
    verify(dragonBallService, times(1)).getSpecificCharacter(anyString());
  }

  @Test
  void givenUsernameAndCharacterIdShouldDeleteFavoriteCharacterFromUser() {
    when(favoriteRepository.findByUserNameAndCharacterId(anyString(), anyString()))
        .thenReturn(Flux.just(FavoriteEntity.builder()
            .id(3L)
            .build()));
    when(favoriteRepository.delete(any()))
        .thenReturn(Mono.empty());
    Mono<GenericResponse> response = favoriteService.deleteFavoriteCharacterFromUser(RandomStringUtils.randomAlphabetic(10),
        RandomStringUtils.randomAlphabetic(10));
    assertEquals("Response should be OK", StatusCodeEnum.OK.getCode(), response.block().getCode());
    verify(favoriteRepository, times(1)).findByUserNameAndCharacterId(anyString(), anyString());
    verify(favoriteRepository, times(1)).delete(any());
  }
}
