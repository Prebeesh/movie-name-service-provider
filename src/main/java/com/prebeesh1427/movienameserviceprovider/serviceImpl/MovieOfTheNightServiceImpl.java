package com.prebeesh1427.movienameserviceprovider.serviceImpl;

import com.prebeesh1427.movienameserviceprovider.ApiClient;
import com.prebeesh1427.movienameserviceprovider.adapters.MovieOfTheNightAdapter;
import com.prebeesh1427.movienameserviceprovider.client.ShowsApi;
import com.prebeesh1427.movienameserviceprovider.dto.MovieSearchResultsDto;
import com.prebeesh1427.movienameserviceprovider.exceptions.InvalidInputException;
import com.prebeesh1427.movienameserviceprovider.model.ShowType;
import com.prebeesh1427.movienameserviceprovider.service.MovieService;
import com.prebeesh1427.movienameserviceprovider.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Optional;

@Service
@Primary
public class MovieOfTheNightServiceImpl implements MovieService {

	private static final Logger logger = LoggerFactory.getLogger(MovieOfTheNightServiceImpl.class);

	private final ApiClient feignClient;
	private final MovieOfTheNightAdapter adapter;

	public MovieOfTheNightServiceImpl(final ApiClient feignClient, final MovieOfTheNightAdapter adapter) {
		this.feignClient = feignClient;
		this.adapter = adapter;
	}

	@Override
	@Cacheable(
		value = "movies",
		key = "T(org.springframework.util.StringUtils).trimAllWhitespace(#searchText).toLowerCase() + ':' + #countryCode.toLowerCase()"
	)
	public ResponseEntity<MovieSearchResultsDto> getMovies(final String searchText, final String countryCode) {
		if (!ValidationUtil.isValidCountryCode(countryCode)) {
			logger.warn("Invalid country code received: {}", countryCode);
			throw new InvalidInputException(String.format("Country code %s is invalid", countryCode));
		}
		if (!ValidationUtil.isValidSearchText(searchText)) {
			logger.warn("Invalid search text received: '{}'.", searchText);
			throw new InvalidInputException(String.format("Search text %s is invalid", searchText));
		}

		final ShowsApi showsApiClient = feignClient.buildClient(ShowsApi.class);
		logger.debug("Calling third-party API for searchText='{}', countryCode='{}'", searchText, countryCode);
		final var shows = showsApiClient.searchShowsByTitle(searchText, countryCode, ShowType.MOVIE, "episode", "en");
		logger.info("Third-party call completed. Results count: {}", shows != null ? shows.size() : 0);

		final MovieSearchResultsDto result = Optional.ofNullable(adapter.transform(shows))
				.orElseGet(MovieSearchResultsDto::new);

		return ResponseEntity.ok(result);
	}
}
