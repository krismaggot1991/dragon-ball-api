package com.pichincha.sp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.pichincha.services.server.models.CharactersResponse;
import com.pichincha.services.server.models.FavoriteCharacterResponse;
import com.pichincha.services.server.models.FavoritesRequest;
import com.pichincha.services.server.models.GenericResponse;
import com.pichincha.services.server.models.LoginResponse;
import com.pichincha.services.server.models.LoginUserRequest;
import com.pichincha.services.server.models.RegisterUserRequest;
import com.pichincha.services.server.models.SpecificCharacterResponse;
import com.pichincha.sp.service.DragonBallService;
import com.pichincha.sp.service.FavoriteService;
import com.pichincha.sp.service.UserService;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DragonBallController.class)
class DragonBallControllerTest {

  @MockBean
  DragonBallService dragonBallService;
  @MockBean
  UserService userService;
  @MockBean
  FavoriteService favoriteService;
  @Autowired
  DragonBallController dragonBallController;
  @Mock
  ServerWebExchange exchange;
  @Mock
  ServerHttpRequest serverHttpRequest;
  @Mock
  HttpHeaders headers;

  @Test
  void givenValidRequestShouldReturnAllCharacters() {
    CharactersResponse response = new CharactersResponse();
    when(exchange.getRequest())
        .thenReturn(serverHttpRequest);
    when(serverHttpRequest.getHeaders())
        .thenReturn(headers);
    when(dragonBallService.getAllCharacters())
        .thenReturn(Mono.just(response));
    StepVerifier.create(dragonBallController.
            supportDragonBallV1CharactersGet(String.valueOf(UUID.randomUUID()), RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5), exchange))
        .expectNext(ResponseEntity.ok(response)).verifyComplete();
  }

  @Test
  void givenValidRequestShouldReturnSpecificCharacter() {
    SpecificCharacterResponse response = new SpecificCharacterResponse();
    when(exchange.getRequest())
        .thenReturn(serverHttpRequest);
    when(serverHttpRequest.getHeaders())
        .thenReturn(headers);
    when(dragonBallService.getSpecificCharacter(anyString()))
        .thenReturn(Mono.just(response));
    StepVerifier.create(dragonBallController.
            supportDragonBallV1CharactersIdGet(RandomStringUtils.randomAlphabetic(5), String.valueOf(UUID.randomUUID()),
                RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5), exchange))
        .expectNext(ResponseEntity.ok(response)).verifyComplete();
  }

  @Test
  void givenValidRequestShouldRegisterFavoriteCharacter() {
    GenericResponse response = new GenericResponse();
    when(exchange.getRequest())
        .thenReturn(serverHttpRequest);
    when(serverHttpRequest.getHeaders())
        .thenReturn(headers);
    when(favoriteService.registerFavoriteCharacter(any(FavoritesRequest.class)))
        .thenReturn(Mono.just(response));
    StepVerifier.create(dragonBallController.
            supportDragonBallV1FavoritesPost(String.valueOf(UUID.randomUUID()), RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5), new FavoritesRequest()
                    .characterId(RandomStringUtils.randomAlphabetic(5))
                    .username(RandomStringUtils.randomAlphabetic(5))
                , exchange))
        .expectNext(ResponseEntity.ok(response)).verifyComplete();
  }

  @Test
  void givenValidRequestShouldDeleteFavoriteCharacterFromUser() {
    GenericResponse response = new GenericResponse();
    when(exchange.getRequest())
        .thenReturn(serverHttpRequest);
    when(serverHttpRequest.getHeaders())
        .thenReturn(headers);
    when(favoriteService.deleteFavoriteCharacterFromUser(anyString(), anyString()))
        .thenReturn(Mono.just(response));
    StepVerifier.create(dragonBallController.
            supportDragonBallV1FavoritesUsernameCharacterIdDelete(RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5),
                String.valueOf(UUID.randomUUID()), RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5), exchange))
        .expectNext(ResponseEntity.ok(response)).verifyComplete();
  }

  @Test
  void givenValidRequestShouldGetFavoriteFromUser() {
    FavoriteCharacterResponse response = new FavoriteCharacterResponse();
    when(exchange.getRequest())
        .thenReturn(serverHttpRequest);
    when(serverHttpRequest.getHeaders())
        .thenReturn(headers);
    when(favoriteService.getFavoriteFromUser(anyString()))
        .thenReturn(Mono.just(response));
    StepVerifier.create(dragonBallController.
            supportDragonBallV1FavoritesUsernameGet(RandomStringUtils.randomAlphabetic(5),
                String.valueOf(UUID.randomUUID()), RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5), exchange))
        .expectNext(ResponseEntity.ok(response)).verifyComplete();
  }

  @Test
  void givenValidRequestShouldLoginUser() {
    LoginResponse response = new LoginResponse();
    when(exchange.getRequest())
        .thenReturn(serverHttpRequest);
    when(serverHttpRequest.getHeaders())
        .thenReturn(headers);
    when(userService.loginUser(any(LoginUserRequest.class)))
        .thenReturn(Mono.just(response));
    StepVerifier.create(dragonBallController.
            supportDragonBallV1LoginPost(String.valueOf(UUID.randomUUID()), RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5), new LoginUserRequest()
                    .email(RandomStringUtils.randomAlphabetic(5))
                    .password(RandomStringUtils.randomAlphabetic(5))
                , exchange))
        .expectNext(ResponseEntity.ok(response)).verifyComplete();
  }

  @Test
  void givenValidRequestShouldRegisterUser() {
    GenericResponse response = new GenericResponse();
    when(exchange.getRequest())
        .thenReturn(serverHttpRequest);
    when(serverHttpRequest.getHeaders())
        .thenReturn(headers);
    when(userService.registerUser(any(RegisterUserRequest.class)))
        .thenReturn(Mono.just(response));
    StepVerifier.create(dragonBallController.
            supportDragonBallV1UserPost(String.valueOf(UUID.randomUUID()), RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5), new RegisterUserRequest()
                    .userName(RandomStringUtils.randomAlphabetic(5))
                    .email(RandomStringUtils.randomAlphabetic(5))
                    .password(RandomStringUtils.randomAlphabetic(5))
                , exchange))
        .expectNext(ResponseEntity.ok(response)).verifyComplete();
  }

}
