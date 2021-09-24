package org.enodation.moviecatalogservice.services;

import org.enodation.moviecatalogservice.models.CatalogItem;
import org.enodation.moviecatalogservice.models.Movie;
import org.enodation.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfoService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getMovieName(), movie.getMovieDesc(), rating.getRating());
	}

	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "N/A", rating.getRating());
	}
}
