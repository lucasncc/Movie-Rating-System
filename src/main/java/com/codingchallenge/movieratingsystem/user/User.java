package com.codingchallenge.movieratingsystem.user;

import java.time.LocalDate;
import java.util.HashMap;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

public class User {
	
	private Integer id;
	
	private Integer points;
	
	@NotNull(message="Username cant be null")
	@NotEmpty(message="Username cant be empty")
	@NotBlank(message="Username cant be blank")
	@Size(min=3, message="Username should have at least 3 characters")
	private String username;
	
	@NotNull(message="Email cant be null")
	@NotEmpty(message="Email cant be empty")
	@NotBlank(message="Email cant be blank")
	@Email(message="Email not valid")
	private String email;
	
	private HashMap<String, Boolean> profile = new HashMap<String, Boolean>();
	
	@Past(message = "Birthdate must be a past date")
	private LocalDate birthDate;
	
	protected User() {
		
	}
	
	public User(Integer id, Integer points, String username, String email, LocalDate birthDate) {
		super();
		this.id = id;
		this.points = points;
		this.username = username;
		this.email = email;
		this.birthDate = birthDate;
		
		this.profile.put("READER", true);
		this.profile.put("BASIC", false);
		this.profile.put("ADVANCED", false);
		this.profile.put("MODERATOR", false);
	}
	
	public void addPoint(User user) {
		user.setPoints(user.getPoints()+1);
		user.updateProfile(user);
	}
	
	public void updateProfile(User user) {
		int userPoints = user.getPoints().intValue();
		if(userPoints < 20) {
			if(!user.getProfile().get("READER")) {
				user.getProfile().replace("READER", true);
			}
		} else if (userPoints < 100) {
			if(!user.getProfile().get("BASIC")) {
				user.getProfile().replace("BASIC", true);
			}
		} else if (userPoints < 1000) {
			if(!user.getProfile().get("ADVANCED")) {
				user.getProfile().replace("ADVANCED", true);
			}
		} else {
			if(!user.getProfile().get("MODERATOR")) {
				user.getProfile().replace("MODERATOR", true);
			}
		}
	}
	
	public void setModerator(User user) {
		if(!user.getProfile().get("READER")) {
			user.getProfile().replace("READER", true);
		}
		if(!user.getProfile().get("BASIC")) {
			user.getProfile().replace("BASIC", true);
		}
		if(!user.getProfile().get("ADVANCED")) {
			user.getProfile().replace("ADVANCED", true);
		}
		if(!user.getProfile().get("MODERATOR")) {
			user.getProfile().replace("MODERATOR", true);
		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public HashMap<String, Boolean> getProfile() {
		return profile;
	}

	public void setProfile(HashMap<String, Boolean> profile) {
		this.profile = profile;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", points=" + points + ", username=" + username + ", email=" + email + ", profile="
				+ profile + ", birthDate=" + birthDate + "]";
	}
	
}
