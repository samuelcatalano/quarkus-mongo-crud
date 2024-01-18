package com.mongo.crud.example.repository.base;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.panache.common.exception.PanacheQueryException;

import org.bson.types.ObjectId;

import java.util.List;

public interface RepositoryCRUDInterface<E extends PanacheMongoEntity> {

  E persistCollection(E entity) throws PanacheQueryException;

  E findCollectionByName(String name);

  E updateCollection(ObjectId id, E entity) throws PanacheQueryException;

  List<E> findAllCollections();

  void deleteCollectionById(ObjectId id) throws PanacheQueryException;

}
