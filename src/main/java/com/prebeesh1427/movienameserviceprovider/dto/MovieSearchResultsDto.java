package com.prebeesh1427.movienameserviceprovider.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieSearchResultsDto {
	@Builder.Default
	private final List<MoviesDto> results = new ArrayList<>();

	public List<MoviesDto> getResults() {
		return Collections.unmodifiableList(results);
	}

	public void setResults(List<MoviesDto> results) {
		this.results.clear();
		if (results != null) {
			this.results.addAll(results);
		}
	}
}
