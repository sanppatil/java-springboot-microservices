package org.enodation.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.enodation.moviecatalogservice.models.CatalogItem;
import org.enodation.moviecatalogservice.models.Movie;
import org.enodation.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating userRating = restTemplate.getForObject("http://RATING-DATA-SERVICE/rating/users/" + userId,
				UserRating.class);

		return userRating.getRatingList().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getMovieName(), movie.getMovieDesc(), rating.getRating());
		}).collect(Collectors.toList());
	}

}
