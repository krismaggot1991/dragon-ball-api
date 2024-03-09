package com.pichincha.sp.controller;

import static lombok.AccessLevel.PRIVATE;

import com.pichincha.services.server.SupportApi;
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
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DragonBallController implements SupportApi {

  DragonBallService dragonBallService;
  UserService userService;
  FavoriteService favoriteService;

  @Override
  public Mono<ResponseEntity<CharactersResponse>> supportDragonBallV1CharactersGet(String xGuid, String xProcess, String xFlow,
      ServerWebExchange exchange) {
    return dragonBallService.getAllCharacters().map(result -> ResponseEntity.ok().body(result));
  }

  @Override
  public Mono<ResponseEntity<SpecificCharacterResponse>> supportDragonBallV1CharactersIdGet(String id, String xGuid, String xProcess, String xFlow,
      ServerWebExchange exchange) {
    return dragonBallService.getSpecificCharacter(id).map(result -> ResponseEntity.ok().body(result));
  }

  @Override
  public Mono<ResponseEntity<GenericResponse>> supportDragonBallV1FavoritesPost(String xGuid, String xProcess, String xFlow,
      FavoritesRequest favoritesRequest, ServerWebExchange exchange) {
    return favoriteService.registerFavoriteCharacter(favoritesRequest).map(result -> ResponseEntity.ok().body(result));
  }

  @Override
  public Mono<ResponseEntity<GenericResponse>> supportDragonBallV1FavoritesUsernameCharacterIdDelete(String username, String characterId,
      String xGuid, String xProcess, String xFlow, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<FavoriteCharacterResponse>> supportDragonBallV1FavoritesUsernameGet(String username, String xGuid, String xProcess,
      String xFlow, ServerWebExchange exchange) {
    return favoriteService.getFavoriteFromUser(username).map(result -> ResponseEntity.ok().body(result));
  }

  @Override
  public Mono<ResponseEntity<LoginResponse>> supportDragonBallV1LoginPost(String xGuid, String xProcess, String xFlow,
      LoginUserRequest loginUserRequest, ServerWebExchange exchange) {
    return userService.loginUser(loginUserRequest).map(result -> ResponseEntity.ok().body(result));
  }

  @Override
  public Mono<ResponseEntity<GenericResponse>> supportDragonBallV1UserPost(String xGuid, String xProcess, String xFlow,
      RegisterUserRequest registerUserRequest, ServerWebExchange exchange) {
    return userService.registerUser(registerUserRequest).map(result -> ResponseEntity.ok().body(result));
  }
}
