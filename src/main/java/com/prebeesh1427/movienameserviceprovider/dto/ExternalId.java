package com.prebeesh1427.movienameserviceprovider.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExternalId {
	private IdAndUrl imdb;
	private IdAndUrl tmdb;
	private IdAndUrl wiki_data;
}

