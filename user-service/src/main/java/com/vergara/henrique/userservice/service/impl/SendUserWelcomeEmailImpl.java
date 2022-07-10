package com.vergara.henrique.userservice.service.impl;

import com.vergara.henrique.userservice.config.rabbitmq.RabbitMQMessageProducer;
import com.vergara.henrique.userservice.model.request.CreateUserRequest;
import com.vergara.henrique.userservice.model.request.WelcomeEmailRequest;
import com.vergara.henrique.userservice.service.SendUserWelcomeEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendUserWelcomeEmailImpl implements SendUserWelcomeEmail {

  private final RabbitMQMessageProducer producer;

  @Value("${rabbitmq.exchanges.internal}")
  private String internalExchange;

  @Value("${rabbitmq.routing-keys.internal-notification}")
  private String internalNotificationRoutingKey;

  public void sendWelcomeEmail(CreateUserRequest createUserRequest) {
    log.info("## Publishing email notification to queue.");

    WelcomeEmailRequest emailRequest = WelcomeEmailRequest.builder()
        .userEmail(createUserRequest.getEmail())
        .userName(createUserRequest.getName()).build();

    try {
      producer.publish(emailRequest, internalExchange, internalNotificationRoutingKey);
    } catch (Exception e) {
      log.error("Error publishing to queue. ", e.getMessage());
      return;
    }

    log.info("## Published with success.");
  }

}

