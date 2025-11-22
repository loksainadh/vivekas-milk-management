package com.milk.vivekas.controller;

import com.milk.vivekas.dao.UserRepository;
import com.milk.vivekas.dto.LoginRequest;
import com.milk.vivekas.dto.RegisterRequest;
import com.milk.vivekas.exception.BadRequestException;
import com.milk.vivekas.exception.ResourceNotFoundException;
import com.milk.vivekas.model.User;

import com.milk.vivekas.service.UserServiceImpl;
import com.milk.vivekas.utill.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;






import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;


    
    
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        User user = new User();
        user.setName(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        userService.registerUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/loginuser")
    public ResponseEntity<String> loginuser(@RequestBody LoginRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        if (auth.isAuthenticated()) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            if (authentication.isAuthenticated()) {
                return jwtUtil.generateToken(email);
            }
            throw new BadRequestException("Invalid email or password");

        } catch (BadCredentialsException e) {
            throw new BadRequestException("Invalid email or password");
        } catch (UsernameNotFoundException e) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
    }



    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/retunuserdetails")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    	
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
