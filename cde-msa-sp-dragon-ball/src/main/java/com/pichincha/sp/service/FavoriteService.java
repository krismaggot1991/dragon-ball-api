package com.pichincha.sp.service;

import com.pichincha.services.server.models.FavoritesRequest;
import com.pichincha.services.server.models.GenericResponse;
import reactor.core.publisher.Mono;

public interface FavoriteService {

  Mono<GenericResponse> registerFavoriteCharacter(FavoritesRequest favoritesRequest);
}
