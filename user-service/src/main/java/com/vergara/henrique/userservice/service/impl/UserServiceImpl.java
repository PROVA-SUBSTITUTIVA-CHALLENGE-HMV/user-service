package com.vergara.henrique.userservice.service.impl;

import static com.vergara.henrique.userservice.util.enums.UserStatus.INACTIVE;

import com.vergara.henrique.userservice.exception.EmailAlreadyRegisteredException;
import com.vergara.henrique.userservice.exception.LoginException;
import com.vergara.henrique.userservice.exception.NotFoundException;
import com.vergara.henrique.userservice.model.entity.User;
import com.vergara.henrique.userservice.model.request.CreateUserRequest;
import com.vergara.henrique.userservice.model.request.UpdateUserRequest;
import com.vergara.henrique.userservice.repository.UserRepository;
import com.vergara.henrique.userservice.service.UserService;
import com.vergara.henrique.userservice.util.mapper.UserMapper;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  @Override
  public User login(String email, String password) {
    final var user = userRepository.findByEmail(email);

    if (user.isEmpty()) {
      throw new NotFoundException("User email not found");
    }

    User foundUser = user.get();

    if (Objects.equals(foundUser.getPassword(), password)) {
      return foundUser;
    }

    throw new LoginException("User email or password incorrect.");
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User findById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("User not found")));
  }

  @Override
  public User createUser(CreateUserRequest request) {
    final var newUserEmail = request.getEmail();

    userRepository.findByEmail(newUserEmail)
        .ifPresent(user -> {
          throw new EmailAlreadyRegisteredException(
              String.format("The informed e-mail: [%s] is already registered",
                  newUserEmail)
          );
        });

    final var userEntity = userMapper.toUser(request);
    return userRepository.save(userEntity);
  }

  @Override
  public User updateUserById(Long id, UpdateUserRequest request) {
    final var userEntity =
        userRepository.findById(id).orElseThrow(() -> new NotFoundException(
            String.format("User {%d} not found", id)
        ));

    final var newUser = userMapper.updateUser(userEntity, request);
    return userRepository.save(newUser);
  }

  @Override
  public void deleteUserById(Long id) {
    final var userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("a"));
    userEntity.setStatus(INACTIVE);
    userRepository.save(userEntity);
  }
}
