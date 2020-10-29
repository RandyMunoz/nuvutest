package com.nuvu.api.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvu.api.people.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findUserByUsernameAndPassword(String username, String password);
	
	public User findUserByUsername(String username);
}
