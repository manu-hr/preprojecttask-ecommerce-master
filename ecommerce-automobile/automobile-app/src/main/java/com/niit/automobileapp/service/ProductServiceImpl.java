package com.niit.automobileapp.service;

import com.niit.automobileapp.exception.MatchingIdException;
import com.niit.automobileapp.exception.NoDocumentException;
import com.niit.automobileapp.model.Product;
import com.niit.automobileapp.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProduct(String id) throws NoDocumentException {
        Optional<Product> isProduct = productRepository.findById(id);
        if(isProduct.isPresent())
            return isProduct.get();
        throw new NoDocumentException();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) throws MatchingIdException {
        try {
            String id = new ObjectId().toString();
            product.setProductId(id);
            return productRepository.insert(product);
        } catch( Exception e) {
            throw new MatchingIdException();
        }
    }

    @Override
    public Product updateProduct(Product product) throws NoDocumentException {
        Optional<Product> isProduct = productRepository.findById(product.getProductId());
        if(isProduct.isPresent())
            return productRepository.save(product);
        throw new NoDocumentException();
    }

    @Override
    public Boolean deleteProduct(String productId) throws NoDocumentException {
        Optional<Product> isProduct = productRepository.findById(productId);
        if(isProduct.isPresent()) {
            productRepository.deleteById(productId);


            return true;
        }
        throw new NoDocumentException();
    }
}
