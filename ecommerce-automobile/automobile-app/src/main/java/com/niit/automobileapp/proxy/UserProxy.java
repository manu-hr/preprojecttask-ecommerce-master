package com.niit.automobileapp.proxy;

import com.niit.automobileapp.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-auth-app", url="localhost:8888")
public interface UserProxy {

    @PostMapping("/register")
    ResponseEntity<?> sendUserDataToAuthApp(@RequestBody UserDTO userDTO);

    @PutMapping("/edit")
    ResponseEntity<?> editUserDataInAuthApp(@RequestBody UserDTO userDTO);

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteUserDataInAuthApp(@RequestBody String UserId);


}
