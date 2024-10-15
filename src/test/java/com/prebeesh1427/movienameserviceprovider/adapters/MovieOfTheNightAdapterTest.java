//package com.prebeesh1427.movienameserviceprovider.adapters;
//
//import com.prebeesh1427.movienameserviceprovider.dto.MovieSearchResultsDto;
//import com.prebeesh1427.movienameserviceprovider.model.HorizontalImage;
//import com.prebeesh1427.movienameserviceprovider.model.Show;
//import com.prebeesh1427.movienameserviceprovider.model.ShowImageSet;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.mock;
//
//public class MovieOfTheNightAdapterTest {
//
//    @Test
//    public void testTransform_NullShows() {
//        MovieOfTheNightAdapter adapter = new MovieOfTheNightAdapter();
//        MovieSearchResultsDto result = adapter.transform(null);
//        assertNull(result);
//    }
//
//    @Test
//    public void testTransform_EmptyShows() {
//        MovieOfTheNightAdapter adapter = new MovieOfTheNightAdapter();
//        List<Show> shows = new ArrayList<>();
//        MovieSearchResultsDto result = adapter.transform(shows);
//        assertNull(result);
//    }
//
//    @Test
//    public void testTransform_SingleShow() {
//        MovieOfTheNightAdapter adapter = new MovieOfTheNightAdapter();
//        Show show = mock(Show.class);
//        Mockito.when(show.getTitle()).thenReturn("Title");
//        Mockito.when(show.getImageSet()).thenReturn(mock(ShowImageSet.class));
//        Mockito.when(show.getImageSet().getHorizontalPoster()).thenReturn(mock(HorizontalImage.class));
//        Mockito.when(show.getImageSet().getHorizontalPoster().getW480()).thenReturn("Poster URL");
//        Mockito.when(show.getStreamingOptions()).thenReturn(mock(StreamingOptions.class));
//        Mockito.when(show.getStreamingOptions().getCountry()).thenReturn(mock(Country.class));
//        Mockito.when(show.getStreamingOptions().getCountry().getLocation()).thenReturn(mock(Location.class));
//        Mockito.when(show.getStreamingOptions().getCountry().getLocation().getService()).thenReturn(mock(Service.class));
//        Mockito.when(show.getStreamingOptions().getCountry().getLocation().getService().getName()).thenReturn("Service Name");
//        Mockito.when(show.getStreamingOptions().getCountry().getLocation().getService().getImageSet()).thenReturn(mock(ImageSet.class));
//        Mockito.when(show.getStreamingOptions().getCountry().getLocation().getService().getImageSet().getWhiteImage()).thenReturn("Service Icon URL");
//        Mockito.when(show.getStreamingOptions().getCountry().getLocation().getUrl()).thenReturn("Streaming URL");
//
//        List<Show> shows = new ArrayList<>();
//        shows.add(show);
//        MovieSearchResultsDto result = adapter.transform(shows);
//
//        assertEquals(1, result.getResults().size());
//        assertEquals("Title", result.getResults().get(0).getName());
//        assertEquals("Poster URL", result.getResults().get(0).getPicture());
//        assertEquals(1, result.getResults().get(0).getLocations().size());
//        assertEquals("Service Name", result.getResults().get(0).getLocations().get(0).getName());
//        assertEquals("Streaming URL", result.getResults().get(0).getLocations().get(0).getUrl());
//        assertEquals("Service Icon URL", result.getResults().get(0).getLocations().get(0).getIcon());
//    }
//
//    @Test
//    public void testGetMovieLocations_NullShow() {
//        MovieOfTheNightAdapter adapter = new MovieOfTheNightAdapter();
//        List<MovieLocation> result = adapter.getMovieLocations(null);
//        assertNull(result);
//    }
//
//    @Test
//    public void testGetMovieLocations_EmptyShow() {
//        MovieOfTheNightAdapter adapter = new MovieOfTheNightAdapter();
//        Show show = mock(Show.class);
//        Mockito.when(show.getStreamingOptions()).thenReturn(mock(StreamingOptions.class));
//        Mockito.when(show.getStreamingOptions().getCountry()).thenReturn(mock(Country.class));
//        Mockito.when(show.getStreamingOptions().getCountry().getLocation()).thenReturn(mock(Location.class));
//
//        List<MovieLocation> result = adapter.getMovieLocations(show);
//        assertEquals(0, result.size());
//    }
//
//    @Test
//    public void testGetExternalIds_NullShow() {
//        MovieOfTheNightAdapter adapter = new MovieOfTheNightAdapter();
//        ExternalId result = adapter.getExternalIds(null);
//        assertNull(result);
//    }
//
//    @Test
//    public void testGetExternalIds_EmptyShow() {
//        MovieOfTheNightAdapter adapter = new MovieOfTheNightAdapter();
//        Show show = mock(Show.class);
//
//        ExternalId result = adapter.getExternalIds(show);
//        assertNull(result.getImdb());
//        assertNull(result.getTmdb());
//    }
//}