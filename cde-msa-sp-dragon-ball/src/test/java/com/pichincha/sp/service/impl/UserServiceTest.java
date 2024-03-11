package com.pichincha.sp.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

import com.pichincha.services.server.models.GenericResponse;
import com.pichincha.services.server.models.LoginResponse;
import com.pichincha.services.server.models.LoginUserRequest;
import com.pichincha.services.server.models.RegisterUserRequest;
import com.pichincha.sp.domain.enums.StatusCodeEnum;
import com.pichincha.sp.domain.jpa.UserEntity;
import com.pichincha.sp.repository.UserRepository;
import com.pichincha.sp.service.UserService;
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
@ContextConfiguration(classes = {UserServiceImpl.class},
    initializers = ConfigDataApplicationContextInitializer.class)
class UserServiceTest {

  @Autowired
  UserService userService;
  @MockBean
  UserRepository userRepository;

  @Test
  void givenValidRequestShouldRegisterUser() {
    when(userRepository.findByUserNameOrEmail(anyString(), anyString()))
        .thenReturn(Flux.empty());
    when(userRepository.save(any()))
        .thenReturn(Mono.just(UserEntity.builder().build()));
    Mono<GenericResponse> response = userService.registerUser(new RegisterUserRequest()
        .userName(RandomStringUtils.randomAlphabetic(10))
        .email(RandomStringUtils.randomAlphabetic(10))
        .password(RandomStringUtils.randomAlphabetic(10))
    );
    assertEquals("Response should be OK", StatusCodeEnum.OK.getCode(), response.block().getCode());
    verify(userRepository, times(1)).findByUserNameOrEmail(anyString(), anyString());
    verify(userRepository, times(1)).save(any());
  }

  @Test
  void givenValidRequestWhenFindByUserNameOrEmailReturnValuesShouldRetrieveUserAlreadyRegistered() {
    when(userRepository.findByUserNameOrEmail(anyString(), anyString()))
        .thenReturn(Flux.just(UserEntity.builder().build()));
    Mono<GenericResponse> response = userService.registerUser(new RegisterUserRequest()
        .userName(RandomStringUtils.randomAlphabetic(10))
        .email(RandomStringUtils.randomAlphabetic(10))
        .password(RandomStringUtils.randomAlphabetic(10))
    );
    assertEquals("Response should be USER_ALREADY_EXISTS", StatusCodeEnum.USER_ALREADY_EXISTS.getCode(), response.block().getCode());
    verify(userRepository, times(1)).findByUserNameOrEmail(anyString(), anyString());
    verify(userRepository, times(0)).save(any());
  }

  @Test
  void givenValidRequestShouldLoginUser() {
    when(userRepository.findByEmailAndPassword(anyString(), anyString()))
        .thenReturn(Flux.just(UserEntity.builder()
            .userName(RandomStringUtils.randomAlphabetic(10))
            .email(RandomStringUtils.randomAlphabetic(10))
            .build()));
    Mono<LoginResponse> response = userService.loginUser(new LoginUserRequest()
        .email(RandomStringUtils.randomAlphabetic(10))
        .password(RandomStringUtils.randomAlphabetic(10))
    );
    assertEquals("Response should be OK", StatusCodeEnum.OK.getCode(), response.block().getCode());
    assertNotNull("Not null expected", response.block().getData().getUserName());
    assertNotNull("Not null expected", response.block().getData().getEmail());
    verify(userRepository, times(1)).findByEmailAndPassword(anyString(), anyString());
  }

  @Test
  void givenValidRequestShouldRetrieveLoginIncorrect() {
    when(userRepository.findByEmailAndPassword(anyString(), anyString()))
        .thenReturn(Flux.empty());
    Mono<LoginResponse> response = userService.loginUser(new LoginUserRequest()
        .email(RandomStringUtils.randomAlphabetic(10))
        .password(RandomStringUtils.randomAlphabetic(10))
    );
    assertEquals("Response should be LOGIN_INCORRECT", StatusCodeEnum.LOGIN_INCORRECT.getCode(), response.block().getCode());
    verify(userRepository, times(1)).findByEmailAndPassword(anyString(), anyString());
  }
}
