package com.prebeesh1427.movienameserviceprovider.dto;



public class ExternalId{
	
	private IdAndUrl imdb;
	private IdAndUrl tmdb;
	private IdAndUrl wiki_data;

	public ExternalId() {
		
	}

	public IdAndUrl getImdb() {
		return imdb;
	}

	public void setImdb(IdAndUrl imdb) {
		this.imdb = imdb;
	}

	public IdAndUrl getTmdb() {
		return tmdb;
	}

	public void setTmdb(IdAndUrl tmdb) {
		this.tmdb = tmdb;
	}

	public IdAndUrl getWiki_data() {
		return wiki_data;
	}

	public void setWiki_data(IdAndUrl wiki_data) {
		this.wiki_data = wiki_data;
	}
}

