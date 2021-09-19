package org.enodation.ratingdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.enodation.ratingdataservice.models.Rating;
import org.enodation.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingDataResource {

	@RequestMapping("/{movieId}")
	public Rating getRatingByMovieId(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4, "Suspenseful");
	}

	@RequestMapping("/users/{userId}")
	public UserRating getRatingByUserId(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(new Rating("1234", 5, "Very good experience, Superb direction..."),
				new Rating("4567", 2, "Poor special effects"));
		UserRating userRating = new UserRating();
		userRating.setRatingList(ratings);
		return userRating;
	}
}
