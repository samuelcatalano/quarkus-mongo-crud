package com.mongo.crud.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import com.mongo.crud.example.domain.Product;
import com.mongo.crud.example.exception.BusinessException;
import com.mongo.crud.example.repository.ProductRepository;

/**
 * Service class for managing {@link Product} entities.
 */
@ApplicationScoped
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  /**
   * Constructs a new ProductService with the specified.
   * @param productRepository The repository for managing categories.
   */
  @Inject
  public ProductService(final ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Saves a category.
   * @param product The category to be saved.
   * @return The saved category.
   * @throws BusinessException If an error occurs during the save process.
   */
  public Product save(final Product product) throws BusinessException {
    try {
      return productRepository.persistCollection(product);
    } catch (final Exception e) {
      log.error("Error persisting product! {}", e.getMessage(), e);
      throw new BusinessException("Error persisting product! ", e);
    }
  }
}
