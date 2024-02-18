package com.user.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ratings {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
