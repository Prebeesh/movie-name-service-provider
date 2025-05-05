package com.prebeesh1427.movienameserviceprovider.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieLocation {
	private String icon;
	private String id;
	private String display_name;
	private String name;
	private String url;
}