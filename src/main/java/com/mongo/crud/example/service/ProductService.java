package com.mongo.crud.example.service;

import io.quarkus.panache.common.exception.PanacheQueryException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import com.mongo.crud.example.domain.Product;
import com.mongo.crud.example.exception.BusinessException;
import com.mongo.crud.example.repository.CategoryRepository;
import com.mongo.crud.example.repository.ProductRepository;

/**
 * Service class for managing {@link Product} entities.
 */
@ApplicationScoped
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  /**
   * Constructs a new ProductService with the specified.
   * @param productRepository The repository for managing categories.
   */
  @Inject
  public ProductService(final ProductRepository productRepository, final CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  /**
   * Saves a product.
   * @param product The category to be saved.
   * @return The saved product.
   * @throws BusinessException If an error occurs during the save process.
   */
  public Product save(final Product product) throws BusinessException {
    try {
      final var category = categoryRepository.findByIdOptional(product.getCategory().id)
          .orElseThrow(() -> new PanacheQueryException("Collection was not found for this category id"));
      product.setCategory(category);
      return productRepository.persistCollection(product);
    } catch (final Exception e) {
      log.error("Error persisting product! {}", e.getMessage(), e);
      throw new BusinessException("Error persisting product! ", e);
    }
  }
}
