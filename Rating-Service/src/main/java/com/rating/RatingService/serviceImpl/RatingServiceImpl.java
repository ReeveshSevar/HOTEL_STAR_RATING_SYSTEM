package com.rating.RatingService.serviceImpl;

import com.rating.RatingService.exception.ResourceNotFoundException;
import com.rating.RatingService.model.Rating;
import com.rating.RatingService.repository.RatingRepository;
import com.rating.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class RatingServiceImpl implements RatingService
{
    @Autowired
    private RatingRepository repository;
    @Override
    public Rating create(Rating rating) {
        return repository.save(rating);
    }

    @Override
    public void deleteRating(String ratingId) {
        Optional<Rating> opt = repository.findById(ratingId);
        if (opt.isPresent())
            repository.deleteById(ratingId);
        else
            throw new ResourceNotFoundException("Rating Not Found");
    }

    @Override
    public List<Rating> getRatings() {
        return repository.findAll();
    }
    @Override
    public List<Rating> getRatingByUserId(String userId) {
       return repository.findByUserId(userId);
    }
    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }
}
