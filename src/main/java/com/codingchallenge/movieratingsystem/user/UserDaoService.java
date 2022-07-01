package com.codingchallenge.movieratingsystem.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 3;
	
	
	static {
		users.add(new User(1, "andre", "123456", "andre@itau.com.br", LocalDate.of(2022, 4, 14)));
		users.add(new User(2, "maria", "platinum123", "maria@itau.com.br", LocalDate.of(2022, 4, 14)));
		users.add(new User(3, "joao", "pass123word", "joao@itau.com.br", LocalDate.of(2022, 4, 14)));
	}
	
	// Method to list all users
	public List<User> findAll() {
		return users;		
	}
	
	// Method to save new user
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		if(user.getPoints()==null) {
			user.setPoints(0);
		}
		if(user.getProfile()==null) {
			user.setProfile("READER");
		}
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		users.add(user);
		return user;
	}
	
	// Method to find user by id
	public User findById(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	// Method to delete user by id
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	public interface UserRepository extends JpaRepository<User, String>{ 
	    User findByUsername(String username); 
	}
	

	
}
