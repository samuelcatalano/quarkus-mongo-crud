package com.mongo.crud.example.repository.base;

import io.quarkus.panache.common.exception.PanacheQueryException;

import com.mongo.crud.example.domain.Category;
import com.mongo.crud.example.domain.base.BaseEntity;
import com.mongo.crud.example.exception.CollectionNotFoundException;

import org.bson.types.ObjectId;

import java.util.List;

public interface RepositoryCRUDInterface<E extends BaseEntity> {

  E persistCollection(E entity) throws PanacheQueryException;

  E findCollectionByName(String name);

  E updateCollection(ObjectId id, E entity) throws PanacheQueryException;

  List<E> findAllCollections();

  void deleteCollectionById(ObjectId id) throws PanacheQueryException;

}
