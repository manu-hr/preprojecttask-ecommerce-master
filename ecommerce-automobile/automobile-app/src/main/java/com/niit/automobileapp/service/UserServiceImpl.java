package com.niit.automobileapp.service;

import com.niit.automobileapp.domain.SignupDetails;
import com.niit.automobileapp.domain.UserDTO;
import com.niit.automobileapp.exception.MatchingIdException;
import com.niit.automobileapp.exception.NoDocumentException;
import com.niit.automobileapp.model.User;
import com.niit.automobileapp.proxy.UserProxy;
import com.niit.automobileapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProxy userProxy;

    @Override
    public User addUser(SignupDetails user) throws MatchingIdException {
        try {
            String userId = new ObjectId().toString();
            UserDTO userDTO = new UserDTO(userId, user.getEmail(),user.getPassword(),"ROLE_USER");
            ResponseEntity response = userProxy.sendUserDataToAuthApp(userDTO);
            if(response.getStatusCode() == HttpStatus.OK) {
                User userDetails = new User(userId ,user.getName(),user.getEmail(),user.getContactNo(),"ROLE_USER",user.getAddress());
                return userRepository.insert(userDetails);
            }
            return null;
        }catch (Exception e) {
            throw new MatchingIdException();
        }
    }

    @Override
    public User getUserById(String email) throws NoDocumentException {
        Optional<User> isUser = userRepository.findById(email);
        if(isUser.isPresent())
            return isUser.get();
        throw new NoDocumentException();
    }

    @Override
    public User updateUser(SignupDetails user, String id) throws NoDocumentException {
        try {
            UserDTO userDTO = new UserDTO(id, user.getEmail(),user.getPassword(),"ROLE_USER");
            ResponseEntity response = userProxy.editUserDataInAuthApp(userDTO);
            if(response.getStatusCode() == HttpStatus.OK) {
                Optional<User> isUser = userRepository.findById(id);
                if(isUser.isPresent()){
                    User userDetails = new User(id ,user.getName(),user.getEmail(),user.getContactNo(),"ROLE_USER",user.getAddress());
                    return userRepository.save(userDetails);
                }
            }
            return null;
        }catch (Exception e) {
            throw new NoDocumentException();
        }
    }

    @Override
    public Boolean deleteUser(String userId) throws NoDocumentException {
        try {
            ResponseEntity<?> authAppResponse = userProxy.deleteUserDataInAuthApp(userId);
            if(authAppResponse.getStatusCode() == HttpStatus.OK) {
                Optional<User> isUser = userRepository.findById(userId);
                if(isUser.isPresent()){
                    userRepository.deleteById(userId);
                    return true;
                }
                return false;
            }
            return false;
        }catch (Exception e) {
            throw new NoDocumentException();

        }

    }
}
