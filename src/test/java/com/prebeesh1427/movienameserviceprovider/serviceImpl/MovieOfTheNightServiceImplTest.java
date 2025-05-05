package com.prebeesh1427.movienameserviceprovider.serviceImpl;

import com.prebeesh1427.movienameserviceprovider.ApiClient;
import com.prebeesh1427.movienameserviceprovider.adapters.MovieOfTheNightAdapter;
import com.prebeesh1427.movienameserviceprovider.client.ShowsApi;
import com.prebeesh1427.movienameserviceprovider.dto.MovieSearchResultsDto;
import com.prebeesh1427.movienameserviceprovider.exceptions.InvalidInputException;
import com.prebeesh1427.movienameserviceprovider.model.Show;
import com.prebeesh1427.movienameserviceprovider.model.ShowType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieOfTheNightServiceImplTest {

    private static final String VALID_COUNTRY_CODE = "us";
    private static final String INVALID_COUNTRY_CODE = "invalid";
    private static final String VALID_SEARCH_TEXT = "Inception";
    private static final String INVALID_SEARCH_TEXT = "";

    @Mock
    private ApiClient feignClient;

    @Mock
    private MovieOfTheNightAdapter adapter;

    @Mock
    private ShowsApi showsApiClient;

    @InjectMocks
    private MovieOfTheNightServiceImpl movieOfTheNightService;

    @Test
    @DisplayName("Should throw InvalidInputException for invalid country code")
    void testGetMovies_InvalidCountryCode() {
        assertThrows(InvalidInputException.class, () ->
                movieOfTheNightService.getMovies(VALID_SEARCH_TEXT, INVALID_COUNTRY_CODE));
    }

    @Test
    @DisplayName("Should throw InvalidInputException for invalid search text")
    void testGetMovies_InvalidSearchText() {
        assertThrows(InvalidInputException.class, () ->
                movieOfTheNightService.getMovies(INVALID_SEARCH_TEXT, VALID_COUNTRY_CODE));
    }

    @Test
    @DisplayName("Should return movies for valid country code and search text")
    void testGetMovies_ValidCountryCodeAndSearchText() {
        // Arrange
        when(feignClient.buildClient(ShowsApi.class)).thenReturn(showsApiClient);
        List<Show> shows = Collections.emptyList();
        when(showsApiClient.searchShowsByTitle(VALID_SEARCH_TEXT, VALID_COUNTRY_CODE, ShowType.MOVIE, "episode", "en"))
                .thenReturn(shows);

        MovieSearchResultsDto expectedDto = new MovieSearchResultsDto();
        when(adapter.transform(shows)).thenReturn(expectedDto);

        // Act
        ResponseEntity<MovieSearchResultsDto> response = movieOfTheNightService.getMovies(VALID_SEARCH_TEXT, VALID_COUNTRY_CODE);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isSameAs(expectedDto);
    }
}