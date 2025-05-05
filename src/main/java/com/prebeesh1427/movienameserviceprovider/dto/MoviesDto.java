package com.prebeesh1427.movienameserviceprovider.dto;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoviesDto {

	private String id;
	private String name;
	private String picture;
	@JsonInclude(value = Include.NON_EMPTY)
	@Builder.Default
	private Set<MovieLocation> locations = new HashSet<>();
	@JsonInclude(value = Include.NON_EMPTY)
	private ExternalId external_ids;
	
}


