package com.niit.automobileapp.service;

import com.niit.automobileapp.exception.MatchingIdException;
import com.niit.automobileapp.exception.NoDocumentException;
import com.niit.automobileapp.model.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(String id) throws NoDocumentException;
    List<Product> getAllProducts();
    Product addProduct(Product product) throws MatchingIdException;
    Product updateProduct(Product product) throws NoDocumentException;
    Boolean deleteProduct(String productId) throws NoDocumentException;
}
