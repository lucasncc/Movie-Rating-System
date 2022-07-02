package com.codingchallenge.movieratingsystem.rating;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.codingchallenge.movieratingsystem.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rating {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull(message="idMovie cant be null")
	@NotEmpty(message="idMovie cant be empty")
	@NotBlank(message="idMovie cant be blank")
	private String idMovie;
	
	@NotNull(message="rating cant be null")
	private Integer rating;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(String idMovie) {
		this.idMovie = idMovie;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", idMovie=" + idMovie + ", rating=" + rating + "]";
	}
	
	
	
	
	
}
