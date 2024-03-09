package com.pichincha.sp.domain.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum StatusCodeEnum {

  OK("200", "OK");
  String code;
  String message;

  StatusCodeEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
