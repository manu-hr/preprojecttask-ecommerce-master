package com.niit.automobileapp.service;

import com.niit.automobileapp.exception.MatchingIdException;
import com.niit.automobileapp.exception.NoDocumentException;
import com.niit.automobileapp.model.Cart;
import com.niit.automobileapp.model.Product;

public interface CartService {
    Cart addToCart(Cart item) throws MatchingIdException;
    Cart getCartItemsByUser(String userEmail) throws NoDocumentException;

    Cart updateCartItem(String email, Product item) throws NoDocumentException;

    Boolean deleteCart(String cartId) throws NoDocumentException;
}
