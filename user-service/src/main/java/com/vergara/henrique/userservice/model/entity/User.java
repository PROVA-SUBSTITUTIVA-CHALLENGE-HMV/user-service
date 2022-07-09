package com.vergara.henrique.userservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vergara.henrique.userservice.util.enums.AuthenticationProviderEnum;
import com.vergara.henrique.userservice.util.enums.UserStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(unique = true)
  private String email;

  @JsonIgnore
  @Column(name = "password")
  private String password;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "status")
  private UserStatus status = UserStatus.ACTIVE;
}

