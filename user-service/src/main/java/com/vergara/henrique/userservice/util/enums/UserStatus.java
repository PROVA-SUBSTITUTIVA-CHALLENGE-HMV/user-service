package com.vergara.henrique.userservice.util.enums;

public enum UserStatus {

  ACTIVE("ACTIVE"),
  INACTIVE("INACTIVE");

  private final String status;

  UserStatus(final String status) {
    this.status = status;
  }
}