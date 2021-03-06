package com.vergara.henrique.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoginException extends ResponseStatusException {

  public LoginException(final String message) {
    super(HttpStatus.UNAUTHORIZED, message);
  }
}

