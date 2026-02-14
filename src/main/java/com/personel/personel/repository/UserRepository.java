package com.personel.personel.repository;

import java.util.Optional;

import org.springframework.boot.security.autoconfigure.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);

}
