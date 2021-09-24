package org.enodation.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.enodation.moviecatalogservice.models.CatalogItem;
import org.enodation.moviecatalogservice.models.UserRating;
import org.enodation.moviecatalogservice.services.MovieInfoService;
import org.enodation.moviecatalogservice.services.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
@EnableCircuitBreaker
public class MovieCatalogResource {

	@Autowired
	private UserRatingService userRatingService;

	@Autowired
	private MovieInfoService movieInfoService;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating userRating = userRatingService.getUserRating(userId);
		return userRating.getRatingList().stream().map(rating -> movieInfoService.getCatalogItem(rating))
				.collect(Collectors.toList());
	}

}
