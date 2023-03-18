package com.lcwd.rating.services.impl;


import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repository.RatingRepository;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingUserId(String hotelId) {
        return ratingRepository.findByUserId(hotelId);
    }

    @Override
    public List<Rating> getAllRatingHotelId(String userId) {
        return ratingRepository.findByHotelId(userId);
    }
}
