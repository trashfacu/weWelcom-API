package com.facundom.weWelcom.repository;

import com.facundom.weWelcom.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    boolean existsByRestaurantName(String restaurantName);

}
