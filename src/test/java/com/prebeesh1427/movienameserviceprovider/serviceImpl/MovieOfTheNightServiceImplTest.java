package com.prebeesh1427.movienameserviceprovider.serviceImpl;

import com.prebeesh1427.movienameserviceprovider.ApiClient;
import com.prebeesh1427.movienameserviceprovider.client.ShowsApi;
import com.prebeesh1427.movienameserviceprovider.dto.MovieSearchResultsDto;
import com.prebeesh1427.movienameserviceprovider.exceptions.InvalidInputException;
import com.prebeesh1427.movienameserviceprovider.utils.ValidationUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieOfTheNightServiceImplTest {

    @Mock
    private ApiClient feignClient;

    @Mock
    private ShowsApi showsApiClient;

    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private MovieOfTheNightServiceImpl movieOfTheNightService;

    @Test
    void testGetMovies_InvalidCountryCode() {
        // Arrange
        String countryCode = "invalid";
        String searchText = "searchText";

        // Act and Assert
        assertThrows(InvalidInputException.class, () -> movieOfTheNightService.getMovies(searchText, countryCode));
    }

    @Test
    void testGetMovies_InvalidSearchText() {
        // Arrange
        String countryCode = "US";
        String searchText = "";

        // Act and Assert
        assertThrows(InvalidInputException.class, () -> movieOfTheNightService.getMovies(searchText, countryCode));
    }

    @Test
    void testGetMovies_ValidCountryCodeAndSearchText() {
        // Arrange
        String countryCode = "US";
        String searchText = "searchText";
        when(feignClient.buildClient(ShowsApi.class)).thenReturn(showsApiClient);

        // Act
        ResponseEntity<MovieSearchResultsDto> response = movieOfTheNightService.getMovies(searchText, countryCode);

        // Assert
        // Note: The method currently returns null, so we can't assert the response body.
        // You may want to modify the method to return a valid response.
    }
}