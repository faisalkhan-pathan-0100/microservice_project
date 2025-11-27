package com.faisal.rating.controller;


import com.faisal.rating.entities.Rating;
import com.faisal.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create rating
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    //get all ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating(){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAll());
    }

    //get rating by user id
    @GetMapping("/{id}")
    public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsByUser(id));
    }


    //get rating by hotel id
    @GetMapping("/{id}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsByHotel(id));
    }
}
