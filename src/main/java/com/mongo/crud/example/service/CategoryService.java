package com.mongo.crud.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import com.mongo.crud.example.domain.Category;
import com.mongo.crud.example.exception.BusinessException;
import com.mongo.crud.example.repository.CategoryRepository;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;

/**
 * Service class for managing {@link Category} entities.
 */
@ApplicationScoped
@Slf4j
public class CategoryService {

  private final CategoryRepository categoryRepository;

  /**
   * Constructs a new CategoryService with the specified.
   * @param categoryRepository The repository for managing categories.
   */
  @Inject
  public CategoryService(final CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  /**
   * Saves a category.
   * @param category The category to be saved.
   * @return The saved category.
   * @throws BusinessException If an error occurs during the save process.
   */
  public Category save(final Category category) throws BusinessException {
    try {
      return categoryRepository.persistCollection(category);
    } catch (final Exception e) {
      log.error("Error persisting category! {}", e.getMessage(), e);
      throw new BusinessException("Error persisting category! ", e);
    }
  }

  /**
   * Updates a category with the specified ID.
   * @param id       The ID of the category to update.
   * @param category The updated category.
   * @return The updated category.
   * @throws BusinessException If an error occurs during the update process.
   */
  public Category update(final ObjectId id, final Category category) throws BusinessException {
    Objects.requireNonNull(id, "id must not be null");
    Objects.requireNonNull(category, "Category entity must not be null");

    try {
      return categoryRepository.updateCollection(id, category);
    } catch (final Exception e) {
      log.error("Error updating category! {}", e.getMessage(), e);
      throw new BusinessException("Error updating category! ", e);
    }
  }

  /**
   * Finds a category by name.
   * @param title The title of the category to find.
   * @return The found category or null if not found.
   */
  public Category findCategoryByTitle(final String title) {
    if (title == null || title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or empty");
    }
    return categoryRepository.findCollectionByName(title);
  }

  /**
   * Retrieves all categories.
   * @return List of all categories.
   */
  public List<Category> getAllCategories() throws BusinessException {
    try {
      return categoryRepository.findAllCollections();
    } catch (final Exception e) {
      log.error("Error retrieving all categories! {}", e.getMessage(), e);
      throw new BusinessException("Error retrieving all categories! ", e);
    }
  }

  /**
   * Deletes a category by ID.
   * @param id The ID of the category to delete.
   * @throws BusinessException If an error occurs during the deletion process.
   */
  public void deleteById(final ObjectId id) throws BusinessException {
    Objects.requireNonNull(id, "id must not be null");

    try {
      categoryRepository.deleteCollectionById(id);
    } catch (final Exception e) {
      log.error("Error deleting category by id! {}", e.getMessage(), e);
      throw new BusinessException("Error deleting category by id! ", e);
    }
  }
}
