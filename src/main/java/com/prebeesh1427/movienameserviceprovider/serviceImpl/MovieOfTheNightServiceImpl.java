package com.prebeesh1427.movienameserviceprovider.serviceImpl;

import com.prebeesh1427.movienameserviceprovider.ApiClient;
import com.prebeesh1427.movienameserviceprovider.adapters.MovieOfTheNightAdapter;
import com.prebeesh1427.movienameserviceprovider.auth.ApiKeyAuth;
import com.prebeesh1427.movienameserviceprovider.client.ShowsApi;
import com.prebeesh1427.movienameserviceprovider.data.Country;
import com.prebeesh1427.movienameserviceprovider.data.CountryCode;
import com.prebeesh1427.movienameserviceprovider.dto.MovieSearchResultsDto;
import com.prebeesh1427.movienameserviceprovider.exceptions.InvalidInputException;
import com.prebeesh1427.movienameserviceprovider.model.ShowType;
import com.prebeesh1427.movienameserviceprovider.service.MovieService;
import com.prebeesh1427.movienameserviceprovider.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
//@Primary
public class MovieOfTheNightServiceImpl implements MovieService{

	private static final Logger logger = LoggerFactory.getLogger(MovieOfTheNightServiceImpl.class);

	private final ApiClient feignClient;
	private final MovieOfTheNightAdapter adapter;

	public MovieOfTheNightServiceImpl(ApiClient feignClient, MovieOfTheNightAdapter adapter) {
		this.feignClient=feignClient;
		this.adapter = adapter;
	}
	
	@Override
	public ResponseEntity<MovieSearchResultsDto> getMovies(String searchText, String countryCode) {

		if(!ValidationUtil.isValidCountryCode(countryCode)) {
			throw new InvalidInputException(String.format("Country code %s is invalid", countryCode));
		}
		if(!ValidationUtil.isValidSearchText(searchText)) {
			throw new InvalidInputException(String.format("Search text %s is invalid", searchText));
		}

		var showsApiClient = feignClient.buildClient(ShowsApi.class);
		logger.debug("starting to call the thirdparty");
		var shows = showsApiClient.searchShowsByTitle(searchText, countryCode, ShowType.MOVIE, "episode", "en");
		logger.info("Thirdparty call completed with results " + shows);

		var result = adapter.transform(shows);

		return ResponseEntity.ok().body(result);
	}

}
