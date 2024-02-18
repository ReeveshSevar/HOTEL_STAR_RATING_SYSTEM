package com.rating.RatingService.service;

import com.rating.RatingService.model.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);
    void deleteRating(String ratingId);
    List<Rating> getRatings();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
