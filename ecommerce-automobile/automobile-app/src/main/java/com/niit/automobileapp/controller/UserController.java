package com.niit.automobileapp.controller;

import com.niit.automobileapp.domain.SignupDetails;
import com.niit.automobileapp.exception.MatchingIdException;
import com.niit.automobileapp.exception.NoDocumentException;
import com.niit.automobileapp.model.User;
import com.niit.automobileapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody SignupDetails user) throws MatchingIdException {
        User response = userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/api/get-user")
    public ResponseEntity<?> getUser(HttpServletRequest request) throws NoDocumentException {
        String id =(String) request.getAttribute("userId");
        System.out.println(id);
        User response = userService.getUserById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/api/edit-user")
    public ResponseEntity<?> editUser(HttpServletRequest request, @RequestBody SignupDetails user) throws NoDocumentException {
        String id =(String) request.getAttribute("userId");
        User response = userService.updateUser(user, id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/api/delete-user")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) throws NoDocumentException {
        String userId =(String) request.getAttribute("userId");
        if(userService.deleteUser(userId)) {
            Map<String, String> response = new HashMap<>();
            response.put("msg", "User Account Deleted Successfully");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        throw new NoDocumentException();
    }

}
