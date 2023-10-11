package com.facundom.weWelcom.service;

import com.facundom.weWelcom.entity.Restaurant;
import com.facundom.weWelcom.model.RestaurantDTO;
import com.facundom.weWelcom.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService implements CRUDService <RestaurantDTO, Restaurant>  {

    private static final Logger logger = Logger.getLogger(Restaurant.class);
    private final RestaurantRepository repository;
    private final ObjectMapper mapper;


    @Override
    public void create(RestaurantDTO restaurantDTO) throws Exception {
        if (restaurantDTO == null ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant can't be null");
        }
        if (repository.existsByRestaurantName(restaurantDTO.getRestaurantName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Restaurant name already in database");
        }
        try {
            // Convert DTO to class
            Restaurant restaurant = mapper.convertValue(restaurantDTO ,Restaurant.class);
            repository.save(restaurant);
        }catch (Exception e){
            logger.error("An error occurred while saving the Restaurant");
            throw e;
        }
    }

    @Override
    public RestaurantDTO read(Integer id) throws Exception {
        //validate if the id is not null or less than zero
        if (id == null || id <= 0){
            throw new IllegalArgumentException("Invalid restaurant id");
        }
        //check if the restaurant is in the database
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venue not found"));
        return mapper.convertValue(restaurant, RestaurantDTO.class);
    }

    @Override
    public void delete(Integer id) throws Exception {
        if (id == null || id <= 0){
            throw new IllegalArgumentException("Invalid restaurant id");
        }
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venue not found"));

        repository.deleteById(id);
    }

    @Override
    public void update(RestaurantDTO restaurantDTO) throws Exception {
        if (restaurantDTO == null){
            throw new IllegalArgumentException("Restaurant cannot be null");
        }

        Integer restaurantId = restaurantDTO.getRestaurantId();
        if (restaurantId == null || restaurantId <= 0){
            throw new IllegalArgumentException("Invalid venue id");
        }

        if (!repository.existsById(restaurantId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Restaurant not found");
        }

        Restaurant restaurant = mapper.convertValue(restaurantDTO, Restaurant.class);
        restaurant.setRestaurantId(restaurantId);
        repository.save(restaurant);
    }

    @Override
    public List<RestaurantDTO> getAll() {
        List<Restaurant> restaurantList = repository.findAll();
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        for (Restaurant restaurant : restaurantList){
            restaurantDTOList.add(mapper.convertValue(restaurant, RestaurantDTO.class));
        }
        return restaurantDTOList;
    }
}
