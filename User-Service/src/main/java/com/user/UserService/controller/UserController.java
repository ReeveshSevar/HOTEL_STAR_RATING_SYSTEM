package com.user.UserService.controller;

import com.user.UserService.model.Users;
import com.user.UserService.serviceImpl.UserServiceImpl;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl service;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Users users)
    {
        service.create(users);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
    }
    @GetMapping("/{id}")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Users> getUser(@PathVariable String id)
    {
        Users users = service.getUser(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers()
    {
        List<Users> list = service.getUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id)
    {
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
    }
    public ResponseEntity<Users> ratingHotelFallback(String userId , Exception ex)
    {
        Users users = Users.builder()
                .id("00000")
                .email("dummy@gmail.com")
                .name("Dummy_User")
                .about("Some Services Are Down").build();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
