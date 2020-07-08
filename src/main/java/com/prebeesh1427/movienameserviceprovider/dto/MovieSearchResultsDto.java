package com.prebeesh1427.movienameserviceprovider.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchResultsDto {

	private List<MoviesDto> results = new ArrayList<>();

	public MovieSearchResultsDto() {

	}
	
	public List<MoviesDto> getResults() {
		return results;
	}

	public void setResults(List<MoviesDto> results) {
		this.results = results;
	}
	
}
