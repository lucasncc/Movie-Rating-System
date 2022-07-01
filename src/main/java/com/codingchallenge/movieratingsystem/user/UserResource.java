package com.codingchallenge.movieratingsystem.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
	
	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	/*list user by id number
	 * input - id number
	 * output - HTTP response OK (200) and list the user data in JSON format in the body  of response.
	 * if the user doesnt exist, return Error response Not Found (404) and error message in the body of response
	*/
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findById(id);
		if(user==null)
			throw new UserNotFoundException("id - "+id);
		return user;
	}
	
	
	/*create new user
	 * input - user data in a json containing points, username, email and birthDate
	 * output - HTTP response CREATED (201) and return the registered user location on the header
	*/
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/*delete user by id number
	 * input - id number	 
	 * output - HTTP response OK (200) if user not null
	 * if user doesnt exists, return HTTP Response NOT FOUND (404) 
	*/
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user==null)
			throw new UserNotFoundException("id - "+id);
	}
	
}
