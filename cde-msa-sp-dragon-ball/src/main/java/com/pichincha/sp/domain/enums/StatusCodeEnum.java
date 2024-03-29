package com.pichincha.sp.domain.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum StatusCodeEnum {

  OK("200", "OK"),
  NOT_FOUND("404", "Character not found"),
  USER_ALREADY_EXISTS("406", "User already exists"),
  LOGIN_INCORRECT("406", "Email or password incorrect"),
  BAD_REQUEST_REGISTER_FAVORITE_CHARACTER("400", "Username or character id does not exits."),
  FAVORITE_CHARACTER_ALREADY_REGISTERED("402", "Character already registered by the username provided.");
  String code;
  String message;

  StatusCodeEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
