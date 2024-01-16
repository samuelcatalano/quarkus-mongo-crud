package com.mongo.crud.example.util;

import com.mongo.crud.example.exception.CopyPropertiesException;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Objects;

public final class ObjectUtils {

  private ObjectUtils() {
  }

  /**
   * Copies non-null and non-empty properties from the source object to the destination object
   * using Apache Commons BeanUtils. This method ensures that both the source and destination
   * objects are non-null before attempting to copy properties. If any exception occurs during
   * the property copying process, it is caught and wrapped in a {@code CopyPropertiesException}.
   *
   * <p><strong>Note:</strong> This method relies on the Apache Commons BeanUtils library.
   * Ensure that the library is included in your project's dependencies.
   *
   * @param source      The source object from which properties will be copied.
   *                    Must not be null.
   * @param destination The destination object to which properties will be copied.
   *                    Must not be null.
   * @throws NullPointerException    If either the source or destination object is null.
   * @throws CopyPropertiesException If an error occurs during the property copying process.
   * @see org.apache.commons.beanutils.BeanUtils#copyProperties(Object, Object)
   */
  public static void copyNonNullOrEmptyPropertiesFrom(final Object source, final Object destination) {
    Objects.requireNonNull(source, "Source object cannot be null");
    Objects.requireNonNull(destination, "Destination object cannot be null");

    try {
      BeanUtils.copyProperties(destination, source);
    } catch (final Exception e) {
      throw new CopyPropertiesException("Failed to copy properties", e);
    }
  }
}
