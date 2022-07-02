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

import com.codingchallenge.movieratingsystem.comment.Comment;
import com.codingchallenge.movieratingsystem.comment.CommentRepository;
import com.codingchallenge.movieratingsystem.rating.Rating;
import com.codingchallenge.movieratingsystem.rating.RatingRepository;

@RestController
public class UserResource {

	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private RatingRepository ratingRepository;
	
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
	
	//retrieve All Comments By User Id
	@GetMapping("/users/{id}/comments")
	public List<Comment> retrieveAllUserComments(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id - "+id);
		}
		
		return userOptional.get().getComments();
	}
	
	/*create new comment
	*/
	@PostMapping("/users/{id}/comments")
	public ResponseEntity<Object> createComment(@PathVariable int id, @RequestBody Comment comment) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id - "+id);
		}
		
		User user = userOptional.get();
		
		comment.setUser(user);
		commentRepository.save(comment);		
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(comment.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//retrieve All Ratings By User Id
	@GetMapping("/users/{id}/ratings")
	public List<Rating> retrieveAllUserRatings(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id - "+id);
		}
		
		return userOptional.get().getRatings();
	}
	
	/*create new comment
	*/
	@PostMapping("/users/{id}/ratings")
	public ResponseEntity<Object> createRating(@PathVariable int id, @RequestBody Rating rating) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id - "+id);
		}
		
		User user = userOptional.get();
		
		rating.setUser(user);
		ratingRepository.save(rating);		
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(rating.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
}
