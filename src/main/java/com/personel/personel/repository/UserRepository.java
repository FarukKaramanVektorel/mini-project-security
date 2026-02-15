package com.personel.personel.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.personel.personel.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
	Boolean existsByEmail(String eMail);

}
