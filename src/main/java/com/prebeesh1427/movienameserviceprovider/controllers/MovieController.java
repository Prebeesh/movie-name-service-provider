package com.prebeesh1427.movienameserviceprovider.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@Tag(name = "Movies", description = "APIs for searching movies and their streaming availability")
@RestController
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	private final MovieService movieService;
	public MovieController(MovieService movieService) {
		this.movieService=movieService;
	}

	@Operation(
		summary = "Get movies by search text and country code",
		description = "Returns a list of movies matching the search text and available in the specified country."
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Movies found and returned successfully"),
		@ApiResponse(responseCode = "400", description = "Invalid input parameters"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping(path = "movies/{searchText}/{countryCode}")
	public ResponseEntity<MovieSearchResultsDto> getMovies(
			@Parameter(description = "Text to search for movies", example = "Inception")
			@PathVariable("searchText") String searchText,
			@Parameter(description = "Country code (e.g., 'us', 'in')", example = "us")
			@PathVariable("countryCode") String countryCode) {
		logger.info(String.format("Received a request with searchText: %s, countryCode: %s", searchText, countryCode));
		return movieService.getMovies(searchText, countryCode);
	}
}
