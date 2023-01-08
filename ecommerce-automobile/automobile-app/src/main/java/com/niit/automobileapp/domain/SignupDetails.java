package com.niit.automobileapp.domain;

import com.niit.automobileapp.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDetails {
    private String name, email, contactNo, password, role;
    private Address address;
}
