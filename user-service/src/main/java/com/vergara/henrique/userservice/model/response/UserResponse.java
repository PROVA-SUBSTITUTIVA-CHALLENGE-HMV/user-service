package com.vergara.henrique.userservice.model.response;

import com.vergara.henrique.userservice.util.enums.UserRoleEnum;
import com.vergara.henrique.userservice.util.enums.UserStatus;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;
  private String nome;
  private String email;
}