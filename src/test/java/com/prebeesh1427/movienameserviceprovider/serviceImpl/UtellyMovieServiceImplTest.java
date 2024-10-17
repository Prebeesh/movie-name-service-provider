//package com.prebeesh1427.movienameserviceprovider.serviceImpl;
//
//import com.prebeesh1427.movienameserviceprovider.data.CountryCode;
//import com.prebeesh1427.movienameserviceprovider.dto.MovieSearchResultsDto;
//import com.prebeesh1427.movienameserviceprovider.exceptions.InvalidInputException;
//import com.prebeesh1427.movienameserviceprovider.utils.ValidationUtil;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class UtellyMovieServiceImplTest {
//
//    @Mock
//    private RestTemplate restClient;
//
//    @Mock
//    private ValidationUtil validationUtil;
//
//    @Mock
//    private CountryCode countryCodeList;
//
//    @InjectMocks
//    private UtellyMovieServiceImpl utellyMovieService;
//
//    @Test
//    public void testGetMovies_InvalidCountryCode() {
//        // Arrange
//        String countryCode = "invalid";
//        String searchText = "searchText";
//        when(validationUtil.isValidCountryCode(countryCode)).thenReturn(false);
//
//        // Act and Assert
//        assertThrows(InvalidInputException.class, () -> utellyMovieService.getMovies(searchText, countryCode));
//    }
//
//    @Test
//    public void testGetMovies_InvalidSearchText() {
//        // Arrange
//        String countryCode = "US";
//        String searchText = "";
//        when(validationUtil.isValidCountryCode(countryCode)).thenReturn(true);
//        when(validationUtil.isValidSearchText(searchText)).thenReturn(false);
//
//        // Act and Assert
//        assertThrows(InvalidInputException.class, () -> utellyMovieService.getMovies(searchText, countryCode));
//    }
//
//    @Test
//    public void testGetMovies_ValidCountryCodeAndSearchText_Success() {
//        // Arrange
//        String countryCode = "US";
//        String searchText = "searchText";
//        String url = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=" + searchText + "&country=" + countryCode;
//        when(validationUtil.isValidCountryCode(countryCode)).thenReturn(true);
//        when(validationUtil.isValidSearchText(searchText)).thenReturn(true);
//        ResponseEntity<MovieSearchResultsDto> responseEntity = ResponseEntity.ok(new MovieSearchResultsDto());
//        when(restClient.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, MovieSearchResultsDto.class)).thenReturn(responseEntity);
//
//        // Act
//        ResponseEntity<MovieSearchResultsDto> response = utellyMovieService.getMovies(searchText, countryCode);
//
//        // Assert
//        // Note: You may want to add additional assertions for the response body.
//    }
//
//    @Test
//    public void testGetMovies_ValidCountryCodeAndSearchText_Failure() {
//        // Arrange
//        String countryCode = "US";
//        String searchText = "searchText";
//        String url = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=" + searchText + "&country=" + countryCode;
//        when(validationUtil.isValidCountryCode(countryCode)).thenReturn(true);
//        when(validationUtil.isValidSearchText(searchText)).thenReturn(true);
//        when(restClient.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, MovieSearchResultsDto.class)).thenThrow(new RuntimeException("API call failed"));
//
//        // Act and Assert
//        ResponseEntity<MovieSearchResultsDto> response = utellyMovieService.getMovies(searchText, countryCode);
//        // Assert that the response status is SERVICE_UNAVAILABLE
//        // Note: You may want to add additional assertions for the response body.
//    }
//}