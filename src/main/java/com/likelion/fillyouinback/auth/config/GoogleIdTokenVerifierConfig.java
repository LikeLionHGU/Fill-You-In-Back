package com.likelion.fillyouinback.auth.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleIdTokenVerifierConfig {

  @Value("${custom.google.client-id}")
  private String clientId;

  @Bean
  public GoogleIdTokenVerifier googleIdTokenVerifier() {
    NetHttpTransport transport = new NetHttpTransport();
    JsonFactory jsonFactory = new JacksonFactory();
    return new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
        .setAudience(Collections.singletonList(clientId))
        .build();
  }
}
