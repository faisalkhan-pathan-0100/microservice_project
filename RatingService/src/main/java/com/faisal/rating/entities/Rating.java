package com.faisal.rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ratings")
public class Rating {

    @Id
    private String ratingId;  // for uniquely identify each rating
    private String userId;    // to know which user rate the hotel
    private String hotelId;   // to know for which hotel rating is given
    private int rating;       // what rating given to particular hotel out of 5
    private String feedback;  // given feedback from user to hotel
}
