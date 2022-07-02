package com.codingchallenge.movieratingsystem.user;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.codingchallenge.movieratingsystem.comment.Comment;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer points;
	
	@NotNull(message="Username cant be null")
	@NotEmpty(message="Username cant be empty")
	@NotBlank(message="Username cant be blank")
	@Size(min=3, message="Username should have at least 3 characters")
	private String username;
	
	@NotNull(message="Password cant be null")
	@NotEmpty(message="Password cant be empty")
	@NotBlank(message="Password cant be blank")
	@Size(min=6, message="Password should have at least 6 characters")
	private String password;
	
	@NotNull(message="Email cant be null")
	@NotEmpty(message="Email cant be empty")
	@NotBlank(message="Email cant be blank")
	@Email(message="Email not valid")
	private String email;
	
	private String profile;
	
	@Past(message = "Birthdate must be a past date")
	private LocalDate birthDate;
	
	
	@OneToMany(mappedBy="user")
	private List<Comment> comments;

	
	protected User() {
		
	}

	public User(Integer id, String username, String password, String email, LocalDate birthDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.birthDate = birthDate;
		
		this.points = 0;
		this.profile = "READER";
	}
	
	public void addPoint(User user) {
		user.setPoints(user.getPoints()+1);
		user.updateProfile(user);
	}
	
	public void updateProfile(User user) {
		int userPoints = user.getPoints().intValue();
		if(userPoints < 20 && user.getProfile() != "MODERATOR") {
			user.setProfile("READER");
		} else if (userPoints < 100 && user.getProfile() != "MODERATOR") {
			user.setProfile("BASIC");
		} else if (userPoints < 1000 && user.getProfile() != "MODERATOR") {
			user.setProfile("ADVANCED");
		} else {
			user.setProfile("MODERATOR");
		}
	}
	
	public void setModerator(User user) {
		user.setProfile("MODERATOR");
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", points=" + points + ", username=" + username + ", email=" + email + ", profile="
				+ profile + ", birthDate=" + birthDate + "]";
	}
	
}
