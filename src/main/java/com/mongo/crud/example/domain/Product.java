package com.mongo.crud.example.domain;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.mongo.crud.example.dto.ProductDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@MongoEntity(collection = "product")
public class Product extends PanacheMongoEntity {

  private String title;
  private String description;
  private String ownerId;
  private Double price;
  private Category category;

  /**
   * Construct a new product instance with the given dto.
   * @param dto the dto to create the product
   */
  public Product(final ProductDTO dto) {
    this.title = dto.title();
    this.description = dto.description();
    this.ownerId = dto.ownerId();
    this.price = dto.price();
  }
}
