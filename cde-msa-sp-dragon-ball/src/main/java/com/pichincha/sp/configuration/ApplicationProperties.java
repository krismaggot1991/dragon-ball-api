package com.pichincha.sp.configuration;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Data
@Configuration
@ConfigurationProperties(prefix = "configurations")
@FieldDefaults(level = PRIVATE)
public class ApplicationProperties {

  String basePath;
  DataSize maxInMemorySize;
  Integer connectTimeout;
  Integer readTimeout;
  Services services;

  Postgresql postgresql;

  @Data
  @FieldDefaults(level = PUBLIC)
  public static class Postgresql {

    String host;
    Integer port;
    String dbname;
    String schema;
    String username;
    String password;
    String sslMode;
  }

  @Data
  @FieldDefaults(level = PRIVATE)
  public static class Services {

    DragonBall dragonBall;

  }

  @Data
  @FieldDefaults(level = PRIVATE)
  public static class DragonBall {

    String basePath;
    AllCharacters allCharacters;
    SpecificCharacter specificCharacter;

  }

  @Data
  @FieldDefaults(level = PRIVATE)
  public static class AllCharacters {

    String path;

  }

  @Data
  @FieldDefaults(level = PRIVATE)
  public static class SpecificCharacter {

    String path;

  }
}
