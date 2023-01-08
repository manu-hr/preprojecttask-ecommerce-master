package com.niit.automobileapp.repository;

import com.niit.automobileapp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> { }
