package com.faisal.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private String ratingId;  // for uniquely identify each rating
    private String userId;    // to know which user rate the hotel
    private String hotelId;   // to know for which hotel rating is given
    private int rating;       // what rating given to particular hotel out of 5
    private String feedback;  // given feedback from user to hotel
}
