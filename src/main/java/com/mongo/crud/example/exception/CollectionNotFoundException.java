package com.mongo.crud.example.exception;

public class CollectionNotFoundException extends Exception {

  public CollectionNotFoundException() {
    super();
  }

  public CollectionNotFoundException(final String message) {
    super(message);
  }

  public CollectionNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public CollectionNotFoundException(final Throwable cause) {
    super(cause);
  }

  protected CollectionNotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
