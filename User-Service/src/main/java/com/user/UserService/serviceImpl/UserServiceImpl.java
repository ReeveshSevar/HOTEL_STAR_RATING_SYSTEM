package com.user.UserService.serviceImpl;

import com.user.UserService.exception.ResourceNotFoundException;
import com.user.UserService.external.HotelService;
import com.user.UserService.external.RatingService;
import com.user.UserService.model.Hotel;
import com.user.UserService.model.Ratings;
import com.user.UserService.model.Users;
import com.user.UserService.repository.UserRepository;
import com.user.UserService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;
    @Override
    public Users create(Users users) {
        return repository.save(users);
    }

    @Override
    public Users getUser(String id) {
       Users users = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not found"));
       // Fetching Rating From Rating Service By Using RestTemplate
        Ratings[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/userId/"+users.getId(), Ratings[].class);
        List<Ratings> ratings = Arrays.stream(ratingOfUser).toList();
        List<Ratings> ratingList = ratings.stream().map(rating ->
        {
//            ResponseEntity<Hotel> response = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).toList();

        users.setRatings(ratings);
       return users;
    }

    @Override
    public List<Users> getUsers() {
        return repository.findAll();
    }

    @Override
    public void deleteUser(String id) {
        Optional<Users> opt = repository.findById(id);
        if (opt.isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException("User Not Found");
    }
}
