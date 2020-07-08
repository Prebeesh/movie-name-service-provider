package com.prebeesh1427.movienameserviceprovider.dto;



class ExternalId{
	
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

	class IdAndUrl{
		private String url;
		private String id;
		
		public IdAndUrl() {
			
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
			
	}
}

