package com.pichincha.sp.service;

import com.pichincha.services.server.models.GenericResponse;
import com.pichincha.services.server.models.LoginUserRequest;
import com.pichincha.services.server.models.RegisterUserRequest;
import reactor.core.publisher.Mono;

public interface UserService {

  Mono<GenericResponse> registerUser(RegisterUserRequest registerUserRequest);

  Mono<GenericResponse> loginUser(LoginUserRequest loginUserRequest);
}
