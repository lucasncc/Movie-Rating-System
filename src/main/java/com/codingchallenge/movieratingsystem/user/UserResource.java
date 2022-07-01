package com.codingchallenge.movieratingsystem.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
	private UserRepository userRepository;
	
	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	/*list user by id number
	 * input - id number
	 * output - HTTP response OK (200) and list the user data in JSON format in the body  of response.
	 * if the user doesnt exist, return Error response Not Found (404) and error message in the body of response
	*/
	@GetMapping("/users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("id - "+id);
		
		return user;
	}
	
	
	/*create new user
	 * input - user data in a json containing points, username, email and birthDate
	 * output - HTTP response CREATED (201) and return the registered user location on the header
	*/
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		if(user.getPoints()==null) {
			user.setPoints(0);
		}
		if(user.getProfile()==null) {
			user.setProfile("READER");
		}
		
		User savedUser = userRepository.save(user);
		
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/*delete user by id number
	 * input - id number	 
	 * output - HTTP response OK (200)
	*/
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
}
