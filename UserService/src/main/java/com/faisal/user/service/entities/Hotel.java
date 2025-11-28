package com.faisal.user.service.entities;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String hotelId;
    private String hotelName;
    private String location;
    private String about;
}
