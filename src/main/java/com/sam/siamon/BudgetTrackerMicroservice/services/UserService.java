package com.sam.siamon.BudgetTrackerMicroservice.services;

import com.sam.siamon.BudgetTrackerMicroservice.models.User.User;
import com.sam.siamon.BudgetTrackerMicroservice.models.User.UserDTO;
import com.sam.siamon.BudgetTrackerMicroservice.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public ResponseEntity<User> createUser(UserDTO userDTO) {
        try {
            if (doesUserExist(userDTO.getEmail())) {
                throw new RuntimeException("this user already exists, please try a different email");
            }
            User user = userRepository.save(new User(userDTO));
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<User> updateEmail(UUID id, String email) {
        try {
            if (doesUserExist(email)) {
                throw new RuntimeException("this user already exists, please try a different email");
            }
            Optional<User> userData = userRepository.findById(id);
            if (userData.isPresent()) {
               User _user = userData.get();
               _user.setEmail(email);
               return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
            } else {
                throw new RuntimeException("Something went wrong, could not update user data.");
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<User> updatePassword(UUID id, String password) {
        try {
            Optional<User> userData = userRepository.findById(id);
            if (userData.isPresent()) {
                User _user = userData.get();
                _user.setPassword(password);
                return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
            } else {
                throw new RuntimeException("Something went wrong, could not update user data.");
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<User> deleteUser(UUID id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    private boolean doesUserExist(String email) {
        Optional<User> _user = userRepository.findByEmail(email);
        return _user.isPresent();
    }

}
