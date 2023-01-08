package com.niit.automobileapp.repository;

import com.niit.automobileapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository< User, String> { }
