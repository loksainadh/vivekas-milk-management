package com.milk.vivekas.dao;

import com.milk.vivekas.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Add custom query methods if needed
	  Optional<User> findByEmail(String email);

	    // Optional: Check if user with email exists
	    boolean existsByEmail(String email);

	    // Optional: Find users by name (could be multiple)
	    List<User> findByName(String name);

	    // Optional: Custom query for login (if you don’t use security framework)
	    Optional<User> findByEmailAndPassword(String email, String password);

	}

