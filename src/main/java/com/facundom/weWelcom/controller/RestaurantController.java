package com.facundom.weWelcom.controller;

import com.facundom.weWelcom.exception.RestaurantNotFoundException;
import com.facundom.weWelcom.model.RestaurantDTO;
import com.facundom.weWelcom.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<String> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) throws Exception{
        try {
            restaurantService.create(restaurantDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Restaurant created");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Integer id) {
        try {
            RestaurantDTO restaurantDTO = restaurantService.read(id);
            return ResponseEntity.ok(restaurantDTO);
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/list")
    public List<RestaurantDTO> listRestaurants(){
            return restaurantService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable Integer id, @RequestBody RestaurantDTO restaurantDTO) throws Exception {
        restaurantDTO.setRestaurantId(id);
        restaurantService.update(restaurantDTO);
        return ResponseEntity.ok("Restaurant updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id) throws Exception {
        try {
            restaurantService.delete(id);
            return ResponseEntity.ok("Restaurant deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
