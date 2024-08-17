package com.prebeesh1427.movienameserviceprovider.service;

import org.springframework.http.ResponseEntity;

import com.prebeesh1427.movienameserviceprovider.dto.MovieSearchResultsDto;

public interface MovieService {
	
	ResponseEntity<MovieSearchResultsDto> getMovies(String searchText, String countryCode);
}
