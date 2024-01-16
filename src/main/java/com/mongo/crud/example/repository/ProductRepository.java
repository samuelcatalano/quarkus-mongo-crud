package com.mongo.crud.example.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.exception.PanacheQueryException;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import com.mongo.crud.example.domain.Category;
import com.mongo.crud.example.domain.Product;
import com.mongo.crud.example.repository.base.RepositoryCRUDInterface;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * MongoDB repository for handling operations related to the 'Product' collection.
 * This class is annotated with {@code @ApplicationScoped} to indicate that instances
 * should be managed by the CDI container.
 */
@ApplicationScoped
@Slf4j
public class ProductRepository implements PanacheMongoRepository<Product>, RepositoryCRUDInterface<Product> {

  /**
   * Persists a new category in the MongoDB collection.
   * @param product The product to be persisted.
   * @return The persisted category.
   * @throws PanacheQueryException if an error occurs during the persistence operation.
   */
  @Override
  public Product persistCollection(final Product product) throws PanacheQueryException {
    try {
      persistOrUpdate(product);
      return product;
    } catch (final Exception e) {
      log.error("Error persisting product! {}", e.getMessage(), e);
      throw new PanacheQueryException("Error persisting product! ", e);
    }
  }

  @Override
  public Product findCollectionByName(final String name) {
    return null;
  }

  @Override
  public Product updateCollection(final ObjectId id, final Product entity) throws PanacheQueryException {
    return null;
  }

  @Override
  public List<Product> findAllCollections() {
    return null;
  }

  @Override
  public void deleteCollectionById(final ObjectId id) throws PanacheQueryException {

  }
}
