package com.hotel.HotelService.repository;

import com.hotel.HotelService.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,String>
{
}
