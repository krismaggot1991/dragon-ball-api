package com.pichincha.sp.service.impl;

import static lombok.AccessLevel.PRIVATE;

import com.pichincha.services.server.models.GenericResponse;
import com.pichincha.services.server.models.RegisterUserRequest;
import com.pichincha.sp.domain.enums.StatusCodeEnum;
import com.pichincha.sp.domain.jpa.UserEntity;
import com.pichincha.sp.repository.UserRepository;
import com.pichincha.sp.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

  UserRepository userRepository;

  @Override
  public Mono<GenericResponse> registerUser(RegisterUserRequest registerUserRequest) {
    return userRepository.findByUserNameOrEmail(registerUserRequest.getUserName(), registerUserRequest.getEmail())
        .collectList()
        .filter(List::isEmpty)
        .flatMap(userEntities -> userRepository.save(UserEntity.builder()
                .userName(registerUserRequest.getUserName())
                .email(registerUserRequest.getEmail())
                .password(registerUserRequest.getPassword())
                .createdOn(LocalDateTime.now())
                .build())
            .thenReturn(new GenericResponse()
                .code(StatusCodeEnum.OK.getCode())
                .message(StatusCodeEnum.OK.getMessage())))
        .switchIfEmpty(Mono.defer(() -> Mono.just(new GenericResponse()
            .code(StatusCodeEnum.USER_ALREADY_EXISTS.getCode())
            .message(StatusCodeEnum.USER_ALREADY_EXISTS.getMessage()))));
  }
}
