package com.codingchallenge.movieratingsystem.comment;

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
public class Comment {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull(message="Text cant be null")
	@NotEmpty(message="Text cant be empty")
	@NotBlank(message="Text cant be blank")
	private String text;
	
	private Integer idCommentCitation;
	private Integer idCommentResponse;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	@NotNull(message="idMovie cant be null")
	@NotEmpty(message="idMovie cant be empty")
	@NotBlank(message="idMovie cant be blank")
	private String idMovie;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getIdCommentCitation() {
		return idCommentCitation;
	}
	public void setIdCommentCitation(Integer idCommentCitation) {
		this.idCommentCitation = idCommentCitation;
	}
	public Integer getIdCommentResponse() {
		return idCommentResponse;
	}
	public void setIdCommentResponse(Integer idCommentResponse) {
		this.idCommentResponse = idCommentResponse;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getIdMovie() {
		return idMovie;
	}
	public void setIdMovie(String idMovie) {
		this.idMovie = idMovie;
	}
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", idCommentCitation=" + idCommentCitation
				+ ", idCommentResponse=" + idCommentResponse + ", idMovie=" + idMovie + "]";
	}
}
