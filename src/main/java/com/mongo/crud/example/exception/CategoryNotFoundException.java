package com.mongo.crud.example.exception;

public class CategoryNotFoundException extends Exception {

  public CategoryNotFoundException() {
    super();
  }

  public CategoryNotFoundException(final String message) {
    super(message);
  }

  public CategoryNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public CategoryNotFoundException(final Throwable cause) {
    super(cause);
  }
}
