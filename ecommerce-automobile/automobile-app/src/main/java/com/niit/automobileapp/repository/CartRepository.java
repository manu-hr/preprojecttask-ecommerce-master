package com.niit.automobileapp.repository;

import com.niit.automobileapp.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {

    Optional<Cart> findByUserEmail(String userEmail);
}
