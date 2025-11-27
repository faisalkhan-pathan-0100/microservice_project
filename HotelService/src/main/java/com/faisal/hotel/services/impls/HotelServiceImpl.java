package com.faisal.hotel.services.impls;

import com.faisal.hotel.entities.Hotel;
import com.faisal.hotel.exceptions.ResourceNotFoundException;
import com.faisal.hotel.repositories.HotelRepository;
import com.faisal.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        System.out.println(hotelRepository.findAll());
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getSingleHotel(String hotelId) {
        return hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found"));
    }
}
