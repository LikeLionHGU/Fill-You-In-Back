package com.likelion.fillyouinback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FillYouInBackApplication {

  public static void main(String[] args) {
    SpringApplication.run(FillYouInBackApplication.class, args);
  }
}
