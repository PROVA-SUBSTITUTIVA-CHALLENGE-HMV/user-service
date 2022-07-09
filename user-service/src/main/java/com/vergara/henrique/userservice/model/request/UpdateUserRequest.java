package com.vergara.henrique.userservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateUserRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("nome")
  private String name;

  @JsonProperty("password")
  private String password;
}
