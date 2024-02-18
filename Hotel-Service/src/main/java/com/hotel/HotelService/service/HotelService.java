package com.hotel.HotelService.service;

import com.hotel.HotelService.model.Hotel;

import java.util.List;

public interface HotelService
{
    Hotel createHotel(Hotel hotel);
    Hotel getHotel(String id);
    List<Hotel> getAllHotels();
    void deleteHotel(String id);
}
