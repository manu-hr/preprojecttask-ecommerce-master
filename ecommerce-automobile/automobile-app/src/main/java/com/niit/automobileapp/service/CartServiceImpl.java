package com.niit.automobileapp.service;

import com.niit.automobileapp.exception.MatchingIdException;
import com.niit.automobileapp.exception.NoDocumentException;
import com.niit.automobileapp.model.Cart;
import com.niit.automobileapp.model.CartProduct;
import com.niit.automobileapp.model.Product;
import com.niit.automobileapp.repository.CartRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addToCart(Cart item) throws MatchingIdException {
        try {
            return cartRepository.insert(item);
        } catch (Exception e) {
            throw new MatchingIdException();
        }

    }

    @Override
    public Cart getCartItemsByUser(String userEmail) throws NoDocumentException {
        Optional<Cart> isCartItem =  cartRepository.findByUserEmail(userEmail);
        if(isCartItem.isPresent())
            return isCartItem.get();
        throw new NoDocumentException();
    }

    @Override
    public Cart updateCartItem(String email, Product item) throws NoDocumentException {
        Optional<Cart> isCartItem =  cartRepository.findByUserEmail(email);
        if(isCartItem.isPresent()) {
            boolean isItemFound = false;
            Cart existingItem = isCartItem.get();
            double totalPrice = 0;
            List<CartProduct> existingCartProducts = new java.util.ArrayList<>(List.of(existingItem.getProducts()));
            for(int i=0; i<existingCartProducts.size(); i++) {
                CartProduct temp = existingCartProducts.get(i);
                if(temp.getProductName().equals(item.getProductName())){
                    temp.setQuantity(temp.getQuantity() + 1);
                    temp.setPrice(item.getPrice() * temp.getQuantity());
                    isItemFound = true;
                    break;
                }
            }
            if(!isItemFound) {
                existingCartProducts.add(new CartProduct(item.getProductName(), 1, item.getPrice()));
            }
            CartProduct[] newCartProduct = existingCartProducts.toArray(new CartProduct[0]);

            Cart newCartItem = new Cart(existingItem.getCartId(),existingItem.getUserEmail(),newCartProduct,1000);
            return cartRepository.save(newCartItem);
        }
        else {
            CartProduct newProduct = new CartProduct(item.getProductName(),1, item.getPrice());
            Cart newCartItem = new Cart(new ObjectId().toString() ,email,new CartProduct[] {newProduct},item.getPrice());
            return cartRepository.save(newCartItem);

        }
    }

    @Override
    public Boolean deleteCart(String cartId) throws NoDocumentException {
        Optional<Cart> isCartItem =  cartRepository.findById(cartId);
        if(isCartItem.isPresent()){
            cartRepository.deleteById(cartId);
            return true;
        }
        throw new NoDocumentException();
    }
}
