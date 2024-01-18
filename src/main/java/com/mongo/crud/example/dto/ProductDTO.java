package com.mongo.crud.example.dto;

import org.bson.types.ObjectId;

public record ProductDTO(String title, String description, String ownerId, Double price, ObjectId categoryId) {
}
