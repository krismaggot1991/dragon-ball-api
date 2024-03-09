package com.pichincha.sp.service;

import com.pichincha.services.server.models.FavoriteCharacterResponse;
import com.pichincha.services.server.models.FavoritesRequest;
import com.pichincha.services.server.models.GenericResponse;
import reactor.core.publisher.Mono;

public interface FavoriteService {

  Mono<GenericResponse> registerFavoriteCharacter(FavoritesRequest favoritesRequest);

  Mono<FavoriteCharacterResponse> getFavoriteFromUser(String username);

  Mono<GenericResponse> deleteFavoriteCharacterFromUser(String username, String characterId);
}
