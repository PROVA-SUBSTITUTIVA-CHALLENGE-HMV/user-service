package com.vergara.henrique.userservice.service;

import com.vergara.henrique.userservice.model.request.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public interface SendUserWelcomeEmail {
   void sendWelcomeEmail(CreateUserRequest createUserRequest);
}
