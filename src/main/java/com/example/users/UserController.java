package com.example.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        if(user.getUsername() == null || user.getUsername().isEmpty()) {
            String errorMessage = "Username is required";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        if(user.getEmail() == null || user.getEmail().isEmpty()) {
            String errorMessage = "Email is required";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok(userRepository.save(user));
    }
}