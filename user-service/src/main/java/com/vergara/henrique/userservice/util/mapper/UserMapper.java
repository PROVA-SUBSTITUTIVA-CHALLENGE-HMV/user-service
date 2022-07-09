package com.vergara.henrique.userservice.util.mapper;

import com.vergara.henrique.userservice.model.entity.User;
import com.vergara.henrique.userservice.model.request.CreateUserRequest;
import com.vergara.henrique.userservice.model.request.UpdateUserRequest;
import com.vergara.henrique.userservice.model.response.UserResponse;
import com.vergara.henrique.userservice.util.enums.UserStatus;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserMapper {

  public User toUser(final CreateUserRequest createUserRequest) {
    return User.builder()
        .name(createUserRequest.getName())
        .email(createUserRequest.getEmail())
        .password(createUserRequest.getPassword())
        .status(UserStatus.ACTIVE)
        .build();
  }

  public User updateUser(final User user, final UpdateUserRequest updateUserRequest) {
    user.setName(updateUserRequest.getName());
    user.setPassword(updateUserRequest.getPassword());
    return user;
  }


  public UserResponse toResponse(final User user) {
    return UserResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .status(user.getStatus())
        .build();

  }

  public List<UserResponse> toResponse(final List<User> userList) {
    return userList.stream()
        .map(this::toResponse)
        .collect(Collectors.toList());

  }
}

