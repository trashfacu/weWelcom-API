package com.facundom.weWelcom.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestaurantDTO {
    private Integer restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhoneNumber;
}
