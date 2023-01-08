package com.niit.automobileapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private String cartId;
    private String userEmail;
    private CartProduct[] products;
    private double totalPrice;


}
