package com.likelion.fillyouinback.auth.exception;

public class WrongTokenException extends RuntimeException {
  public WrongTokenException(String message) {
    super(message);
  }
}
