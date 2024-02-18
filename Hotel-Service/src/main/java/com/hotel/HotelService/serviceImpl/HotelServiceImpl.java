package com.hotel.HotelService.serviceImpl;

import com.hotel.HotelService.exception.ResourceNotFoundException;
import com.hotel.HotelService.model.Hotel;
import com.hotel.HotelService.repository.HotelRepository;
import com.hotel.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService
{

    @Autowired
    private HotelRepository repository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        return repository.save(hotel);
    }

    @Override
    public Hotel getHotel(String id) {
        Optional<Hotel> opt = repository.findById(id);
        Hotel hotel ;
        if (opt.isPresent()) {
            hotel = opt.get();
            return hotel;
        }
        else
            throw new ResourceNotFoundException("Hotel Not Found");
    }

    @Override
    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    @Override
    public void deleteHotel(String id) {
        Optional<Hotel> opt = repository.findById(id);
        if (opt.isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException("Hotel Not Found");
    }
}
