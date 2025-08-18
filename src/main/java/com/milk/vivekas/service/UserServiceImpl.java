package com.milk.vivekas.service;

import com.milk.vivekas.dao.UserRepository;
import com.milk.vivekas.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

	@Autowired
	private EmailService emailService;
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder; 

    public User registerUser(User user) {
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        emailService.sendRegistrationEmail(user.getEmail(), user.getName());

        return userRepository.save(user);
    }

    
    public User createUser(User user) {
         
    	emailService.sendRegistrationEmail(user.getEmail(), user.getName());
       
    	
    
        return userRepository.save(user);
    }

   
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

   
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

   
    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setName(user.getName());
                    existing.setEmail(user.getEmail());
                    existing.setPassword(user.getPassword());
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

   
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

	
}
