package com.pichincha.sp.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.client.SSLMode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import reactor.util.annotation.NonNull;

@Configuration
@RequiredArgsConstructor
@EnableR2dbcRepositories(basePackages = "com.pichincha.sp.repository")
public class PostgresConfiguration extends AbstractR2dbcConfiguration {

  private final ApplicationProperties crdProperties;

  @Bean
  @Override
  public @NonNull
  PostgresqlConnectionFactory connectionFactory() {
    return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
        .host(crdProperties.getPostgresql().host)
        .port(crdProperties.getPostgresql().port)
        .schema(crdProperties.getPostgresql().schema)
        .database(crdProperties.getPostgresql().dbname)
        .username(crdProperties.getPostgresql().username)
        .password(crdProperties.getPostgresql().password)
        .sslMode(SSLMode.fromValue(crdProperties.getPostgresql().sslMode))
        .build());
  }
}
