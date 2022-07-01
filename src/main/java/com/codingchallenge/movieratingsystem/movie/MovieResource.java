package com.codingchallenge.movieratingsystem.movie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieResource {
	
	// VALID OMDB API KEY
	// GET KEY AT https://www.omdbapi.com/apikey.aspx
	private String omdbApiKey = "e4217bb2";
	
	/*get movie information by searching title by term using external API (OMDb)
	 * input - term used to search movie title
	 * output - JSON object with movie info
	*/
	@GetMapping("/movies/{term}")
	private Object getMovieByTerm(@PathVariable String term) {
		String newTerm = term.replace(" ", "+");
		String url = "https://www.omdbapi.com/?apikey="+omdbApiKey+"&t="+newTerm;
		RestTemplate restTemplate = new RestTemplate();
		
		Object movie = restTemplate.getForObject(url, Object.class);
		
		return movie;
	}
	
	/*get movie information by imdb id using external API (OMDb)
	 * input - imdb id used to search movie
	 * output - JSON object with movie info
	*/
	@GetMapping("/movies/id/{id}")
	private Object getMovieById(@PathVariable String id) {
		String url = "https://www.omdbapi.com/?apikey="+omdbApiKey+"&i="+id;
		RestTemplate restTemplate = new RestTemplate();
		
		Object movie = restTemplate.getForObject(url, Object.class);
		
		return movie;
	}

}
