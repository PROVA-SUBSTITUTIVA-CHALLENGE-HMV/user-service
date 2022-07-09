package com.vergara.henrique.userservice.controller;

import com.vergara.henrique.userservice.model.request.CreateUserRequest;
import com.vergara.henrique.userservice.model.request.LoginRequest;
import com.vergara.henrique.userservice.model.request.UpdateUserRequest;
import com.vergara.henrique.userservice.model.response.UserResponse;
import com.vergara.henrique.userservice.service.UserService;
import com.vergara.henrique.userservice.util.mapper.UserMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @PostMapping(value = "/login")
  public ResponseEntity<UserResponse> login(
      @RequestBody final LoginRequest loginRequest
  ) {
    final var userEntity =
        userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    final var userResponse = userMapper.toResponse(userEntity);

    // TODO implementar notificação por email para usuario

    return ResponseEntity.status(HttpStatus.OK).body(userResponse);
  }

  @GetMapping
  public ResponseEntity<List<UserResponse>> findAll() {
    final var userList = userService.findAll();
    final var userListResponse =  userMapper.toResponse(userList);
    return ResponseEntity.ok(userListResponse);
  }

  @GetMapping("{id}")
  public ResponseEntity<UserResponse> findById(@PathVariable final Long id) {
    final var userEntity = userService.findById(id);
    final var userResponse =  userMapper.toResponse(userEntity);
    return ResponseEntity.ok(userResponse);
  }

  @PostMapping
  public ResponseEntity<UserResponse> create(
      @RequestBody final CreateUserRequest createUserRequest
  ) {
    final var userEntity = userService.createUser(createUserRequest);
    final var userResponse =  userMapper.toResponse(userEntity);
    //TODO implementar notificacao de boas vindas
    return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
  }

  @PutMapping("{id}")
  public ResponseEntity<UserResponse> update(
      @PathVariable final Long id,
      @RequestBody final UpdateUserRequest updateUserRequest) {
    final var updatedUserEntity = userService.updateUserById(id, updateUserRequest);
    final var userResponse =  userMapper.toResponse(updatedUserEntity);
    return ResponseEntity.ok(userResponse);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<UserResponse> deleteById(
      @PathVariable(name = "id") final Long id) {
    userService.deleteUserById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
