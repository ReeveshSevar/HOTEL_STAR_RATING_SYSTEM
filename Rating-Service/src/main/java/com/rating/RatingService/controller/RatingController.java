package com.rating.RatingService.controller;

import com.rating.RatingService.exception.ResourceNotFoundException;
import com.rating.RatingService.model.Rating;
import com.rating.RatingService.serviceImpl.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController
{
    @Autowired
    private RatingServiceImpl service;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<String> createRating(@RequestBody Rating rating)
    {
        service.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body("Rating Created");
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating()
    {
        List<Rating> list = service.getRatings();
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }
    @DeleteMapping("/{ratingId}")
    public ResponseEntity<String> deleteRating(@PathVariable String ratingId)
    {
        service.deleteRating(ratingId);
        return ResponseEntity.status(HttpStatus.OK).body("Rating Deleted");
    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/userId/{id}")
    public List<Rating> getRatingByUsesId(@PathVariable String id)
    {
        List<Rating> list = service.getRatingByUserId(id);
        if (list.isEmpty())
            throw new ResourceNotFoundException("Rating Not Found");
        else
            return list;
    }
    @GetMapping("/hotelId/{id}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String id)
    {
        List<Rating> list = service.getRatingByHotelId(id);
        if (list.isEmpty())
            throw new ResourceNotFoundException("Rating Not Found");

        else
            return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }
}
