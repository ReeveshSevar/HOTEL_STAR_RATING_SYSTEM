package com.user.UserService.serviceImpl;

import com.user.UserService.exception.ResourceNotFoundException;
import com.user.UserService.external.HotelService;
import com.user.UserService.external.RatingService;
import com.user.UserService.model.Hotel;
import com.user.UserService.model.Ratings;
import com.user.UserService.model.Users;
import com.user.UserService.repository.UserRepository;
import com.user.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

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

       // Fetching Rating By UserId Using RatingService FeignClient
       List<Ratings> ratings = ratingService.getRatingByUsesId(id);

       // Fetching Hotel Using HotelService FeignCLient
       List<Ratings> ratingList = ratings.stream().map(rating ->
       {
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
