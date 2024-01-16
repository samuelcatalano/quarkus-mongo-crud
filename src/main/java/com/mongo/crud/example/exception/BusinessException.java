package com.mongo.crud.example.exception;

public class BusinessException extends Exception {

  public BusinessException() {
    super();
  }

  public BusinessException(final String message) {
    super(message);
  }

  public BusinessException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public BusinessException(final Throwable cause) {
    super(cause);
  }

  protected BusinessException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
