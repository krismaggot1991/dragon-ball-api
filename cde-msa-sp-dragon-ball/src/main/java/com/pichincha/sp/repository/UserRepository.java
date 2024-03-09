package com.pichincha.sp.repository;

import com.pichincha.sp.domain.jpa.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {

  Flux<UserEntity> findByUserNameOrEmail(String userName, String email);

  Flux<UserEntity> findByEmailAndPassword(String email, String password);

  Flux<UserEntity> findByUserName(String userName);
}
