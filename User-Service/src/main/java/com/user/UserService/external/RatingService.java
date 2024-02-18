package com.user.UserService.external;

import com.user.UserService.model.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @PostMapping("/ratings")
    public Ratings createRating(Ratings ratings);
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}
