package com.prebeesh1427.movienameserviceprovider.adapters;

import com.prebeesh1427.movienameserviceprovider.dto.*;
import com.prebeesh1427.movienameserviceprovider.model.Show;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class MovieOfTheNightAdapter {

    private static final Logger log = LoggerFactory.getLogger(MovieOfTheNightAdapter.class);

    /**
     * Transforms a list of Show objects into a MovieSearchResultsDto.
     *
     * @param shows the list of shows to transform
     * @return a MovieSearchResultsDto containing the transformed movies, or an empty result if input is null/empty
     */
    public MovieSearchResultsDto transform(final List<Show> shows) {
        if (shows == null || shows.isEmpty()) {
            log.debug("No results for search");
            return new MovieSearchResultsDto();
        }
        final List<MoviesDto> movies = new ArrayList<>();
        for (final Show show : shows) {
            final MoviesDto movie = new MoviesDto();
            movie.setId(show.getId());
            movie.setName(show.getTitle());
            movie.setPicture(Optional.ofNullable(show.getImageSet())
                    .map(imgSet -> imgSet.getHorizontalPoster())
                    .map(poster -> poster.getW480())
                    .orElse(null));
            movie.setExternal_ids(getExternalIds(show));
            movie.setLocations(getMovieLocations(show));
            movies.add(movie);
        }

        final MovieSearchResultsDto results = new MovieSearchResultsDto();
        results.setResults(movies);
        return results;
    }

    private Set<MovieLocation> getMovieLocations(final Show show) {
        final Set<MovieLocation> result = new HashSet<>();
        if (show == null || show.getStreamingOptions() == null) {
            return result;
        }
        show.getStreamingOptions().forEach((country, locations) -> {
            locations.forEach(streamingOption -> {
                final MovieLocation movieLocation = new MovieLocation();
                movieLocation.setUrl(streamingOption.getLink());
                movieLocation.setName(Optional.ofNullable(streamingOption.getService())
                        .map(service -> service.getName())
                        .orElse(null));
                movieLocation.setIcon(Optional.ofNullable(streamingOption.getService())
                        .map(service -> service.getImageSet())
                        .map(imageSet -> imageSet.getLightThemeImage())
                        .orElse(null));
                result.add(movieLocation);
            });
        });
        return result;
    }

    private ExternalId getExternalIds(final Show show) {
        if (show == null) {
            log.debug("No show to convert");
            return null;
        }
        final ExternalId externalId = new ExternalId();
        final IdAndUrl imdbIdAndUrl = new IdAndUrl();
        final String imdbId = show.getImdbId();
        if (imdbId != null) {
            imdbIdAndUrl.setId(imdbId);
            imdbIdAndUrl.setUrl("https://www.imdb.com/title/" + imdbId);
        }
        externalId.setImdb(imdbIdAndUrl);

        final IdAndUrl tmdbIdAndUrl = new IdAndUrl();
        final String tmdbId = show.getTmdbId();
        if (tmdbId != null) {
            tmdbIdAndUrl.setId(tmdbId);
            tmdbIdAndUrl.setUrl("https://www.themoviedb.org/movie/" + tmdbId);
        }
        externalId.setTmdb(tmdbIdAndUrl);

        final IdAndUrl wikiDataIdAndUrl = new IdAndUrl();
        final String wikiDataId = "Q11424";
        wikiDataIdAndUrl.setId(wikiDataId);
        wikiDataIdAndUrl.setUrl("https://www.wikidata.org/wiki/" + wikiDataId);
        externalId.setWiki_data(wikiDataIdAndUrl);

        return externalId;
    }
}
