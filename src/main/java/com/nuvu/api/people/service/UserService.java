package com.nuvu.api.people.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvu.api.people.entities.User;
import com.nuvu.api.people.repositories.UserRepository;

@Service
public class UserService {

	private static Logger _logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public User getUserByUsernameAndPassword(String username, String password) {
		return userRepository.findUserByUsernameAndPassword(username, password);
	}

	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	public void save(User user) {
		try {
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error("Error in method delete", e.getMessage());
		}
	}

}
