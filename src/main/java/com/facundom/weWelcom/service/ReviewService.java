package com.facundom.weWelcom.service;

import com.facundom.weWelcom.entity.Restaurant;
import com.facundom.weWelcom.entity.Review;
import com.facundom.weWelcom.entity.User;
import com.facundom.weWelcom.model.ReviewDTO;
import com.facundom.weWelcom.repository.RestaurantRepository;
import com.facundom.weWelcom.repository.ReviewRepository;
import com.facundom.weWelcom.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private static final Logger logger = Logger.getLogger(Review.class);
    private final ObjectMapper mapper;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public void create(Integer restaurantId, Integer userId, ReviewDTO reviewDTO) throws Exception {
        try {
            Review review = mapper.convertValue(reviewDTO, Review.class);

            review.setReviewText(reviewDTO.getReviewText());

            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            //Associate to restaurant and user
            review.setRestaurant(restaurant);
            review.setUser(user);


            reviewRepository.save(review);
        }catch (Exception e){
            throw new Exception("Error when writing review" + e.getMessage());
        }
    }

    public List<ReviewDTO> getReviewsByRestaurant(Integer restaurantId) throws Exception {
        try {
            //Get list of reviews of one restaurant
            List<Review> reviewList = reviewRepository.findByRestaurant_RestaurantId(restaurantId);
            //Check if there are any
            if (reviewList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No reviews found for that restaurant");
            }

            List<ReviewDTO> reviewDTOList = new ArrayList<>();
            //Change it to DTO
            for (Review review : reviewList) {
                reviewDTOList.add(mapper.convertValue(review, ReviewDTO.class));
            }
            return reviewDTOList;
        }catch (Exception e){
            throw new Exception("Error getting review by restaurant: " + e.getMessage());
        }
    }

    public void delete (Integer reviewId) throws Exception{

        if (reviewId == null || reviewId <= 0){
            throw new IllegalArgumentException("Invalid id");
        }

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));

        reviewRepository.deleteById(reviewId);
    }
}
