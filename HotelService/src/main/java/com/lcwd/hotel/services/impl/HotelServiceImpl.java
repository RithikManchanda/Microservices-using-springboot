package com.lcwd.hotel.services.impl;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.repositories.HotelRepositories;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepositories hotelRepositories;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepositories.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepositories.findAll();
    }

    @Override
    public Hotel getHotelUsingId(String id) {
        return hotelRepositories.findById(id).orElseThrow(()-> new ResourceNotFoundException("hotel with given id not found"));
    }
}
