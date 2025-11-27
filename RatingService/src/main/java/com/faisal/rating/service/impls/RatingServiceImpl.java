package com.faisal.rating.service.impls;

import com.faisal.rating.entities.Rating;
import com.faisal.rating.repositories.RatingRepository;
import com.faisal.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        String hotelId = UUID.randomUUID().toString();
        rating.setRatingId(hotelId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUser(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotel(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
