package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAllRatings();


    List<Rating> getAllRatingUserId(String id);

    List<Rating> getAllRatingHotelId(String id);




}
