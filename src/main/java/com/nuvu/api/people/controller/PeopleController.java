package com.nuvu.api.people.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuvu.api.people.dto.JwtDTO;
import com.nuvu.api.people.entities.People;
import com.nuvu.api.people.entities.Rol;
import com.nuvu.api.people.entities.User;
import com.nuvu.api.people.security.JwtProvider;
import com.nuvu.api.people.service.PeopleService;
import com.nuvu.api.people.service.UserService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class PeopleController {

	private static Logger _logger = LoggerFactory.getLogger(PeopleController.class);

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody People people) {
		try {
			peopleService.save(people);
			return new ResponseEntity<>("Registro guardado exitosamente", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error("Error in method save", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getListPeople")
	public ResponseEntity<?> getListPeople() {
		try {
			List<People> listPeople = peopleService.getListPeople();
			return new ResponseEntity<List<People>>(listPeople, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error("Error in method getListPeople", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getPeopleById")
	public ResponseEntity<?> getPeopleById(@RequestParam(required = true) Long id) {
		try {
			People people = peopleService.getPeopleById(id);
			if (people != null) {
				return new ResponseEntity<People>(people, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error("Error in method getPeopleById", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/updatePeople")
	public ResponseEntity<?> updatePeople(@RequestBody People people) {
		try {
			People p = peopleService.getPeopleById(people.getId());
			if (p != null) {
				peopleService.save(people);
				return new ResponseEntity<>("Registro actualizado exitosamente", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error("Error in method updatePeople", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<?> delete(@RequestParam(required = true) Long id) {
		try {
			peopleService.delete(id);
			return new ResponseEntity<>("Registro eliminado exitosamente", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error("Error in method delete", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/authentication")
	public ResponseEntity<?> authentication(@RequestParam(required = true) String username,
			@RequestParam(required = true) String password) {
		try {
			if (username != null && password != null) {
				User user = userService.getUserByUsernameAndPassword(username, password);
				if (user != null) {
					Authentication authentication = authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
					SecurityContextHolder.getContext().setAuthentication(authentication);
					String token = jwtProvider.generateToken(authentication);
					UserDetails userDetails = (UserDetails) authentication.getPrincipal();
					JwtDTO jwtDTO = new JwtDTO(token, "Bearer", user.getUsername(), userDetails.getAuthorities());
					return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>("Usuario o contraseña vacios", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error("Error in method authentication", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			if (user.getUsername() != null && user.getPassword() != null && user.getRoles() != null
					&& user.getRoles().size() > 0) {
				User u = userService.getUserByUsername(user.getUsername());
				if (u != null) {
					User newUser = new User();
					newUser.setUsername(user.getUsername());
					newUser.setPassword(passwordEncoder.encode(user.getPassword()));
					newUser.setRoles(user.getRoles());
					userService.save(newUser);

					return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("El usuario ya existe", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>("Faltan datos del usuario", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error("Error in method registerUser", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
