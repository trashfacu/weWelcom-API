package com.facundom.weWelcom.controller;

import com.facundom.weWelcom.entity.Review;
import com.facundom.weWelcom.exception.ApiResponse;
import com.facundom.weWelcom.exception.ReviewNotFoundException;
import com.facundom.weWelcom.model.ReviewDTO;
import com.facundom.weWelcom.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private static final Logger logger = Logger.getLogger(Review.class);


    @PostMapping("/create/{restaurantId}/{userId}")
    public ResponseEntity<ApiResponse> addReview(
            @PathVariable Integer restaurantId,
            @PathVariable Integer userId,
            @RequestBody ReviewDTO reviewDTO
            ){
        try {
            reviewService.create(restaurantId, userId, reviewDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Restaurant review created successfully."));
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error when reviewing."));
        }
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getReviewByRestaurant(@PathVariable Integer restaurantId){
        try {
            List<ReviewDTO> reviewDTOList = reviewService.getReviewsByRestaurant(restaurantId);
            return ResponseEntity.ok(reviewDTOList);
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Internal Server Error"));
        }
    }
}
