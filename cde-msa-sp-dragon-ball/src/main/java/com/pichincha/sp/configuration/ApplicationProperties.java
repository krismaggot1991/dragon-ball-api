package com.pichincha.sp.configuration;

import static lombok.AccessLevel.PRIVATE;

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
}
