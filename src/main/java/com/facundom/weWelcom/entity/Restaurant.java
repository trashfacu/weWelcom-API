package com.facundom.weWelcom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name = "restaurant_address", nullable = false)
    private String restaurantAddress;

    @Column(name = "restaurant_phone_number"    , nullable = false)
    private String restaurantPhoneNumber;
}
