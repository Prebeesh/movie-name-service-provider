package com.prebeesh1427.movienameserviceprovider.serviceImpl;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prebeesh1427.movienameserviceprovider.service.MovieService;
import com.prebeesh1427.movienameserviceprovider.data.CountryCode;
import com.prebeesh1427.movienameserviceprovider.dto.MovieSearchResultsDto;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private RestTemplate restClient;
	
	@Autowired
	private CountryCode countryCodeList;
	
	@Value("${api.movie.host}")
	private String host;
	
	@Value("${api.movie.key}")
	private String key;
	

	Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
	
	@Override
	public ResponseEntity<MovieSearchResultsDto> getMovies(String searchText, String countryCode) {
		
		logger.info("GetMovies Service started");
		if(validateInput(searchText, countryCode)) {
			logger.error("*****************************");
			logger.error("Server side validation failed");
			logger.error("*****************************");
			return new ResponseEntity<MovieSearchResultsDto>(HttpStatus.BAD_REQUEST);
		}
		String url = prepareUrl(searchText,countryCode);
		HttpHeaders header = new HttpHeaders();
		prepareHeader(header);
		HttpEntity<String> requestEntity = new HttpEntity<String>(header);
		try {
			logger.info("Calling the API for movie info");
			ResponseEntity<MovieSearchResultsDto> apiResult = restClient.exchange(url,
					HttpMethod.GET,
					requestEntity,
					MovieSearchResultsDto.class);
			return new ResponseEntity<MovieSearchResultsDto>(apiResult.getBody(),HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Exception occured while calling the API "+ e);
			 return new ResponseEntity<MovieSearchResultsDto>(
			          HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}

	private String prepareUrl(String searchText, String countryCode) {
		
		return "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term="+searchText+"&country="+countryCode;
	}
	
	private boolean validateInput(String searchText, String countryCode) {

		if(searchText == "" || searchText == null || !countryCodeList.contains(countryCode)) {
			return true;
		}
		return false;
	}

	private void prepareHeader(HttpHeaders header) {
		logger.debug("Setting the Header");
		header.add("x-rapidapi-host", host);
		header.add("x-rapidapi-key", key);
		header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
	}

}
