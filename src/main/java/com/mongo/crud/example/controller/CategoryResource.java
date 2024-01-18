package com.mongo.crud.example.controller;

import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import com.mongo.crud.example.domain.Category;
import com.mongo.crud.example.dto.CategoryDTO;
import com.mongo.crud.example.exception.BusinessException;
import com.mongo.crud.example.service.CategoryService;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * JAX-RS resource class for handling category-related operations.
 * This class defines RESTful endpoints for managing categories.
 */
@Slf4j
@Path("/api/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

  private final CategoryService categoryService;

  /**
   * Constructor for CategoryResource.
   * @param categoryService The CategoryService instance to be injected.
   */
  @Inject
  public CategoryResource(final CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  /**
   * POST method to save a new category.
   * @param category The Category object to be saved.
   * @return The saved Category object.
   * @throws InternalServerErrorException if there is an issue saving the category.
   */
  @POST
  public Response saveCategory(final CategoryDTO category) throws InternalServerErrorException {
    try {
      var result = categoryService.save(category);
      return Response.ok(result).build();
    } catch (final BusinessException e) {
      log.error("Error saving category: {}", e.getMessage(), e);
      throw new InternalServerErrorException(e.getMessage(), e);
    }
  }

  /**
   * PUT method to update an existing category by ID.
   * @param id       The ID of the category to be updated.
   * @param category The updated Category object.
   * @return The updated Category object.
   * @throws BusinessException if there is an issue updating the category.
   */
  @Path("{id}")
  @PUT
  public Response updateCategory(@PathParam("id") final ObjectId id, final CategoryDTO category) throws BusinessException {
    try {
      var result = categoryService.update(id, category);
      return Response.ok(result).build();
    } catch (final BusinessException e) {
      log.error("Error updating category: {}", e.getMessage(), e);
      throw new InternalServerErrorException(e.getMessage(), e);
    }
  }

  /**
   * GET method to retrieve a category by title.
   * @param title The title of the category to be retrieved.
   * @return Response with the found Category object or no content if not found.
   */
  @GET
  @Path("/{title}")
  public Response getCategoryByTitle(@PathParam("title") final String title) {
    final CategoryDTO foundCategory = categoryService.findCategoryByTitle(title);
    if (foundCategory.title() == null) {
      return Response.noContent().build();
    }
    return Response.ok(foundCategory).build();
  }

  /**
   * GET method to retrieve all categories.
   * @return Response with a list of all Category objects.
   */
  @GET
  public Response getAllCategories() throws BusinessException {
    final List<CategoryDTO> foundCategories;
    try {
      foundCategories = categoryService.getAllCategories();
      return Response.ok(foundCategories).build();
    } catch (final Exception e) {
      throw new BusinessException(e);
    }
  }

  /**
   * GET method to retrieve all categories.
   * @return Response with a list of all Category objects.
   */
  @DELETE
  @Path("/{id}")
  public Response deleteCategoryById(@PathParam("id") final ObjectId id) throws BusinessException {
    categoryService.deleteById(id);
    return Response.noContent().build();
  }
}
