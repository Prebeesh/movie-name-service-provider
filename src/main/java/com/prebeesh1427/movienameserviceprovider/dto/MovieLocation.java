package com.prebeesh1427.movienameserviceprovider.dto;

import java.util.Objects;

public class MovieLocation{
	
	private String icon;
	private String id;
	private String display_name;
	private String name;
	private String url;
	
	public MovieLocation() {
		
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MovieLocation that = (MovieLocation) o;
		return Objects.equals(getDisplay_name(), that.getDisplay_name()) && Objects.equals(getUrl(), that.getUrl());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDisplay_name(), getUrl());
	}
}