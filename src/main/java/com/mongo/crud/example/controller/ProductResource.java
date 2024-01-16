package com.mongo.crud.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.mongo.crud.example.domain.Category;
import com.mongo.crud.example.domain.Product;
import com.mongo.crud.example.exception.BusinessException;
import com.mongo.crud.example.service.ProductService;

/**
 * JAX-RS resource class for handling product-related operations.
 * This class defines RESTful endpoints for managing products.
 */
@Path("/api/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

  private final ProductService productService;

  /**
   * Constructor for ProductResource.
   * @param productService The ProductResource instance to be injected.
   */
  @Inject
  public ProductResource(final ProductService productService) {
    this.productService = productService;
  }

  /**
   * POST method to save a new product.
   * @param product The Product object to be saved.
   * @return The saved Category object.
   * @throws BusinessException if there is an issue saving the category.
   */
  @POST
  public Product saveProduct(final Product product) throws BusinessException {
    return productService.save(product);
  }
}
