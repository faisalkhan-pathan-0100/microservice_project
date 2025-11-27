package com.faisal.rating.service;

import com.faisal.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create rating
    public Rating createRating(Rating rating);

    //get all rating
    public List<Rating> getAll();

    //get ratings by user
    public List<Rating> getRatingsByUser(String userId);

    //get ratings by hotel
    public List<Rating> getRatingsByHotel(String hotelId);
}
