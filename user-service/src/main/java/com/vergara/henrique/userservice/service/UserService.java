package com.vergara.henrique.userservice.service;

import com.vergara.henrique.userservice.model.entity.User;
import com.vergara.henrique.userservice.model.request.CreateUserRequest;
import com.vergara.henrique.userservice.model.request.UpdateUserRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

  User login(String email, String password);
  List<User> findAll();
  User findById(Long id);
  User createUser(CreateUserRequest request);
  User updateUserById(Long id, UpdateUserRequest request);
  void deleteUserById(Long id);
}
