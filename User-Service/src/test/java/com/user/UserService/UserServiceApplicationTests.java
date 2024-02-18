package com.user.UserService;

import com.user.UserService.external.RatingService;
import com.user.UserService.model.Ratings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
	@Test
	void contextLoads() {
		Ratings ratings = Ratings.builder()
				.rating(5)
				.userId("")
				.hotelId("")
				.feedback("Hello From Feign")
				.build();
		Ratings save = ratingService.createRating(ratings);
		System.out.println("New Rating Created");
	}

}
