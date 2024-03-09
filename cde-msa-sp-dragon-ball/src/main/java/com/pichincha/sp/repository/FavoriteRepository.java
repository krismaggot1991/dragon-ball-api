package com.pichincha.sp.repository;

import com.pichincha.sp.domain.jpa.FavoriteEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FavoriteRepository extends ReactiveCrudRepository<FavoriteEntity, Long> {

  Flux<FavoriteEntity> findByUserNameAndCharacterId(String userName, String characterId);
}
