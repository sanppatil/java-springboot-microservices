package org.enodation.moviecatalogservice.services;

import java.util.Arrays;

import org.enodation.moviecatalogservice.models.Rating;
import org.enodation.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://RATING-DATA-SERVICE/rating/users/" + userId, UserRating.class);
	}

	public UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setRatingList(Arrays.asList(new Rating("0", 0, "None")));
		return userRating;
	}

}
