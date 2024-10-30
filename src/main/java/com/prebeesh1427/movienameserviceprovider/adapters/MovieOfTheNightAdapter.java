package com.prebeesh1427.movienameserviceprovider.adapters;

import com.prebeesh1427.movienameserviceprovider.dto.*;
import com.prebeesh1427.movienameserviceprovider.model.Show;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MovieOfTheNightAdapter {

    private static final Logger log = LoggerFactory.getLogger(MovieOfTheNightAdapter.class);

    public MovieSearchResultsDto transform(List<Show> shows) {
        if (shows == null || shows.isEmpty()) {
            log.debug("No results for search");
            return null;
        }
        List<MoviesDto> movies = new ArrayList<>();
        for(var show : shows) {
            var movie = new MoviesDto();
            movie.setId(show.getId());
            movie.setName(show.getTitle());
            movie.setPicture(show.getImageSet().getHorizontalPoster().getW480());
            movie.setExternal_ids(getExternalIds(show));
            movie.setLocations(getMovieLocations(show));
            movies.add(movie);
        }

        var results = new MovieSearchResultsDto();
        results.setResults(movies);
        return results;
    }

    private Set<MovieLocation> getMovieLocations(Show show) {
        var result = new HashSet<MovieLocation>();
        show.getStreamingOptions().forEach((country, location ) -> {
            location.forEach((streamingOption) -> {
                var movieLocation = new MovieLocation();
                movieLocation.setUrl(streamingOption.getLink());
                movieLocation.setName(streamingOption.getService().getName());
                movieLocation.setIcon(streamingOption.getService().getImageSet().getLightThemeImage());
                result.add(movieLocation);
            });
        });
        return result;
    }

    private ExternalId getExternalIds(Show show) {
        if (show == null) {
            log.debug("No show to convert");
            return null;
        }
        var externalId = new ExternalId();
        var imdbIdAndUrl = new IdAndUrl();
        String imdbId = show.getImdbId();
        if (imdbId != null) {
            imdbIdAndUrl.setId(imdbId);
            imdbIdAndUrl.setUrl("https://www.imdb.com/title/" + imdbId);
        }
        externalId.setImdb(imdbIdAndUrl);

        var tmdbIdAndUrl = new IdAndUrl();
        String tmdbId = show.getTmdbId();
        if (tmdbId != null) {
            tmdbIdAndUrl.setId(tmdbId);
            tmdbIdAndUrl.setUrl("https://www.themoviedb.org/movie/" + tmdbId);
        }
        externalId.setTmdb(tmdbIdAndUrl);

        var wikiDataIdAndUrl = new IdAndUrl();
        String wikiDataId = "https://www.wikidata.org/wiki/Q11424";
        if (wikiDataId != null) {
            wikiDataIdAndUrl.setId(wikiDataId);
            wikiDataIdAndUrl.setUrl("https://www.wikidata.org/wiki/" + wikiDataId);
        }
        externalId.setWiki_data(wikiDataIdAndUrl);

        return externalId;
    }
}
