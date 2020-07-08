package com.prebeesh1427.movienameserviceprovider.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prebeesh1427.movienameserviceprovider.service.MovieService;
import com.prebeesh1427.movienameserviceprovider.dto.MovieSearchResultsDto;

@RestController
public class MovieController {
	
	
	@Autowired
	private MovieService movieService;

	Logger logger = LoggerFactory.getLogger(MovieController.class);

	@GetMapping(path = "movies/{searchText}/{countryCode}")
	public ResponseEntity<MovieSearchResultsDto> getMovies(@PathVariable("searchText") String searchText, @PathVariable("countryCode") String countryCode) {
		
		ResponseEntity<MovieSearchResultsDto>  response = movieService.getMovies(searchText, countryCode);
		logger.debug("Returning the data from controller");
		return response;
	}
}
