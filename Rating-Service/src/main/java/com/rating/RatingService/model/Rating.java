package com.rating.RatingService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rating")
public class Rating {
    @Id
    @UuidGenerator
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
