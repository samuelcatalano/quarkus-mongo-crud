package com.mongo.crud.example.domain;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.mongo.crud.example.dto.CategoryDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@MongoEntity(collection = "category")
public class Category extends PanacheMongoEntity {

  private String title;
  private String description;
  private String ownerId;

  /**
   * Construct a new category instance with the given dto.
   * @param dto the dto to create the category
   */
  public Category(final CategoryDTO dto) {
    this.title = dto.title();
    this.description = dto.description();
    this.ownerId = dto.ownerId();
  }
}
