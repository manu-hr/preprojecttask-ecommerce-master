package com.niit.automobileapp.controller;

import com.niit.automobileapp.exception.NoDocumentException;
import com.niit.automobileapp.model.Cart;
import com.niit.automobileapp.model.Product;
import com.niit.automobileapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/get")
    public ResponseEntity<?> getCartItems(HttpServletRequest request) throws NoDocumentException {
        String email =(String) request.getAttribute("userEmail");
        Cart item = cartService.getCartItemsByUser(email);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCartItem(HttpServletRequest request, @RequestBody Product item) throws NoDocumentException {
        String email =(String) request.getAttribute("userEmail");
        return new ResponseEntity<>(cartService.updateCartItem(email, item), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable String id) throws NoDocumentException {
        return new ResponseEntity<>(cartService.deleteCart(id), HttpStatus.OK);
    }


}
