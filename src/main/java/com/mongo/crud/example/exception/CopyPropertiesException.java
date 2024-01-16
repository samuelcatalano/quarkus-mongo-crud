package com.mongo.crud.example.exception;

public class CopyPropertiesException extends RuntimeException {

  public CopyPropertiesException() {
    super();
  }

  public CopyPropertiesException(final String message) {
    super(message);
  }

  public CopyPropertiesException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public CopyPropertiesException(final Throwable cause) {
    super(cause);
  }

}
