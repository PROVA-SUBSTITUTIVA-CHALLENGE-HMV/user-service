package com.vergara.henrique.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyRegisteredException extends ResponseStatusException {
  public EmailAlreadyRegisteredException(final String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }
}