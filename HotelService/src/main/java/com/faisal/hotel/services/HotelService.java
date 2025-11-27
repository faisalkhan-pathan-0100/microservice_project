package com.faisal.hotel.services;

import com.faisal.hotel.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HotelService {

    //create
    public Hotel createHotel(Hotel hotel);

    //get all hotels
    public List<Hotel> getAll();

    //get single hotels
    public Hotel getSingleHotel(String hotelId);
}
