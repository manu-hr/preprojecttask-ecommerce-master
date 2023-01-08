package com.niit.automobileapp.service;

import com.niit.automobileapp.domain.SignupDetails;
import com.niit.automobileapp.exception.MatchingIdException;
import com.niit.automobileapp.exception.NoDocumentException;
import com.niit.automobileapp.model.User;


public interface UserService {
    User addUser(SignupDetails user) throws MatchingIdException;
    User getUserById(String email) throws NoDocumentException;
    User updateUser(SignupDetails user, String id) throws NoDocumentException;
    Boolean deleteUser(String userId) throws NoDocumentException;

}
