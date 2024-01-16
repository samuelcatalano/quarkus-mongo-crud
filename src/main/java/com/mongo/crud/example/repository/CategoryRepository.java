package com.mongo.crud.example.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.exception.PanacheQueryException;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import com.mongo.crud.example.domain.Category;
import com.mongo.crud.example.repository.base.RepositoryCRUDInterface;
import com.mongo.crud.example.util.ObjectUtils;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

/**
 * MongoDB repository for handling operations related to the 'Category' collection.
 * This class is annotated with {@code @ApplicationScoped} to indicate that instances
 * should be managed by the CDI container.
 */
@ApplicationScoped
@Slf4j
public class CategoryRepository implements PanacheMongoRepository<Category>, RepositoryCRUDInterface<Category> {

  /**
   * Persists a new category in the MongoDB collection.
   * @param category The category to be persisted.
   * @return The persisted category.
   * @throws PanacheQueryException if an error occurs during the persistence operation.
   */
  @Override
  public Category persistCollection(final Category category) throws PanacheQueryException {
    try {
      persistOrUpdate(category);
      return category;
    } catch (final Exception e) {
      log.error("Error persisting category! {}", e.getMessage(), e);
      throw new PanacheQueryException("Error persisting category! ", e);
    }
  }

  /**
   * Updates an existing category in the MongoDB collection based on the given ID.
   * @param id     The ID of the category to be updated.
   * @param entity The updated category entity.
   * @return The updated category.
   * @throws PanacheQueryException if an error occurs during the update operation.
   */
  @Override
  public Category updateCollection(final ObjectId id, final Category entity) throws PanacheQueryException {
    try {
      final var category = findByIdOptional(id)
          .orElseThrow(() -> new PanacheQueryException("Collection was not found for this category id"));
      ObjectUtils.copyNonNullOrEmptyPropertiesFrom(entity, category);
      category.setId(id);
      persistOrUpdate(category);
      return category;
    } catch (final Exception e) {
      log.error("Error updating category! {}", e.getMessage(), e);
      throw new PanacheQueryException("Error updating category! ", e);
    }
  }

  /**
   * Finds a category in the MongoDB collection based on its name.
   * @param name The name of the category to be retrieved.
   * @return The found category, or an empty category if not found.
   */
  @Override
  public Category findCollectionByName(final String name) {
    return (Category) Optional.ofNullable(find("title", name).firstResult())
        .orElse(new Category());
  }

  /**
   * Retrieves all categories from the MongoDB collection.
   * @return A list of all categories.
   * @throws PanacheQueryException if an error occurs during the retrieval operation.
   */
  @Override
  public List<Category> findAllCollections() throws PanacheQueryException {
    try {
      return listAll();
    } catch (final Exception e) {
      log.error("Error retrieving all categories! {}", e.getMessage(), e);
      throw new PanacheQueryException("Error retrieving all categories! ", e);
    }
  }

  /**
   * Deletes a category from the MongoDB collection based on its ID.
   * @param id The ID of the category to be deleted.
   * @throws PanacheQueryException if an error occurs during the deletion operation.
   */
  @Override
  public void deleteCollectionById(final ObjectId id) throws PanacheQueryException {
    try {
      deleteById(id);
    } catch (final Exception e) {
      log.error("Error deleting category! {}", e.getMessage(), e);
      throw new PanacheQueryException("Error deleting category! ", e);
    }
  }
}
