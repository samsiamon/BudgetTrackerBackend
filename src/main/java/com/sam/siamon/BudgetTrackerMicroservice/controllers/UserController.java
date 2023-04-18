package com.sam.siamon.BudgetTrackerMicroservice.controllers;

import com.sam.siamon.BudgetTrackerMicroservice.models.User.User;
import com.sam.siamon.BudgetTrackerMicroservice.models.User.UserDTO;
import com.sam.siamon.BudgetTrackerMicroservice.services.BudgetItemService;
import com.sam.siamon.BudgetTrackerMicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final BudgetItemService budgetItemService;
    @Autowired
    public UserController(
            UserService userService,
            BudgetItemService budgetItemService
    ) {
        this.userService = userService;
        this.budgetItemService = budgetItemService;
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") UUID id) {
        return userService.findById(id);
    }
    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }
    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }
    @PutMapping("/{id}/email")
    public ResponseEntity<User> updateEmail(@PathVariable("id") UUID id, @RequestBody String email) {
        return userService.updateEmail(id, email);
    }
    @PutMapping("/{id}/password")
    public ResponseEntity<User> updatePassword(@PathVariable("id") UUID id, @RequestBody String password) {
        return userService.updatePassword(id, password);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") UUID id) {
        budgetItemService.deleteBudgetItemByUser(id);
        return userService.deleteUser(id);
    }
}