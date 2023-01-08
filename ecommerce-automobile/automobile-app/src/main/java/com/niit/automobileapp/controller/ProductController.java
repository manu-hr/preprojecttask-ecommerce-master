package com.niit.automobileapp.controller;

import com.niit.automobileapp.exception.MatchingIdException;
import com.niit.automobileapp.exception.NoDocumentException;
import com.niit.automobileapp.model.Product;

import com.niit.automobileapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping("/api/product/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) throws MatchingIdException {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }

    @GetMapping("/get-product")
    public ResponseEntity<?> getAllProduct() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PutMapping("/api/product/edit")
    public ResponseEntity<?> editProduct(@RequestBody Product product) throws NoDocumentException {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("/api/product/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) throws NoDocumentException {
        if(productService.deleteProduct(id)) {
            Map<Object, Object> response = new HashMap<>();
            response.put("message" , "Deleted!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoDocumentException();
    }

}
