package com.prebeesh1427.movienameserviceprovider.serviceImpl;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.prebeesh1427.movienameserviceprovider.service.MovieService;
import com.prebeesh1427.movienameserviceprovider.data.CountryCode;
import com.prebeesh1427.movienameserviceprovider.dto.MovieSearchResultsDto;

@Service
//@Primary
public class UtellyMovieServiceImpl implements MovieService{

	private static final Logger logger = LoggerFactory.getLogger(UtellyMovieServiceImpl.class);

	private final RestTemplate restClient;

	private final CountryCode countryCodeList;
	private final String host, key;
	
	public UtellyMovieServiceImpl(RestTemplate restClient,
								  CountryCode countryCodeList,
								  @Value("${api.movie.host}") String host,
								  @Value("${api.movie.key}") String key) {
		this.restClient=restClient;
		this.countryCodeList=countryCodeList;
		this.host=host;
		this.key=key;
	}
	
	@Override
	public ResponseEntity<MovieSearchResultsDto> getMovies(String searchText, String countryCode) {

		if(isInValid(searchText, countryCode)) {
			logger.error("Server side validation failed");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		String url = prepareUrl(searchText,countryCode);
		HttpEntity<String> requestEntity = new HttpEntity<>(prepareHeader(new HttpHeaders()));
		try {
			logger.info("Calling the API for movie info");
			ResponseEntity<MovieSearchResultsDto> apiResult = restClient.exchange(url,
					HttpMethod.GET,
					requestEntity,
                    MovieSearchResultsDto.class);
			logger.info(String.format("Result is %s", apiResult));
			return new ResponseEntity<>(apiResult.getBody(),HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Exception occured while calling the API "+ e);
			 return new ResponseEntity<>(
			          HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}

	private String prepareUrl(String searchText, String countryCode) {
		return String.format("https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=%s&country=%s", searchText, countryCode);
	}

	private boolean isInValid(String searchText, String countryCode) {
		if(StringUtils.hasText(searchText) && countryCodeList.isSupported(countryCode)) {
			return false;
		}
		return true;
	}

	private HttpHeaders prepareHeader(HttpHeaders header) {
		logger.debug("Setting the Header");
		header.add("X-RapidAPI-Host", host);
		header.add("X-RapidAPI-Key", key);
		header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return header;
	}

}
