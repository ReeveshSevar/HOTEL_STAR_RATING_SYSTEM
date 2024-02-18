package com.hotel.HotelService.controller;

import com.hotel.HotelService.model.Hotel;
import com.hotel.HotelService.serviceImpl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelServiceImpl service;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<String> createHotel(@RequestBody Hotel hotel)
    {
        service.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hotel Created");
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') ||  hasAuthority('Admin')")
    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable String id)
    {
        Hotel hotel = service.getHotel(id);
        return hotel;
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels()
    {
        System.out.println("Request Received");
        List<Hotel> list = service.getAllHotels();
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable String id)
    {
        service.deleteHotel(id);
        return ResponseEntity.status(HttpStatus.OK).body("Hotel Deleted");
    }
}
