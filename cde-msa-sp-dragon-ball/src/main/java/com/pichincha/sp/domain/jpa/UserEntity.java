package com.pichincha.sp.domain.jpa;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
@Table(name = "user", schema = "dragonball")
public class UserEntity {

  @Id
  Long id;
  String userName;
  String email;
  String password;
  @Column(value = "created_on")
  LocalDateTime createdOn;
}
