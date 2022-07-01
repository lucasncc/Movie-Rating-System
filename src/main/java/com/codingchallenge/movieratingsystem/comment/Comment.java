package com.codingchallenge.movieratingsystem.comment;

public class Comment {
	
	private Integer id;
	private String text;
	private Integer idCommentCitation;
	private Integer idCommentResponse;
	private Integer idUser;
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
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getIdMovie() {
		return idMovie;
	}
	public void setIdMovie(String idMovie) {
		this.idMovie = idMovie;
	}
	

}
