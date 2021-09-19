package org.enodation.ratingdataservice.models;

public class Rating {

	private String movieId;
	private String reviews;
	private int rating;

	public Rating() {
		super();
	}

	public Rating(String movieId, int rating, String reviews) {
		this.movieId = movieId;
		this.rating = rating;
		this.reviews = reviews;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

}
