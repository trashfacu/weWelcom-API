package com.facundom.weWelcom.repository;

import com.facundom.weWelcom.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Optional<Review> findByUser_UserEmail(String userEmail);

    List<Review> findByRestaurant_RestaurantId(Integer restaurantId);
}
