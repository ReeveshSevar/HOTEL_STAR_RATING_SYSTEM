package com.user.UserService.external;


import com.user.UserService.model.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/hotels/{id}")
    Hotel getHotel(@PathVariable String id);

}
