package com.twilight.components.database;

import com.twilight.annotations.ValidEmail;
import com.twilight.dataTransferObjects.request.RestaurantCreateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    /// Unique ID of the restaurant
    @Id
    private String id;

    /// Name of the restaurant
    private String name;

    private String image;

    @OneToOne
    @MapsId
    @JoinColumn
    /// Merchant of Restaurant
    private Merchant merchant;

    /// Products of the restaurant
    @OneToMany(cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    /// AddressDetails of the restaurant
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;


    @Min(value = 0)
    @Max(value = 5)
    private int rating;

    @OneToMany
    private List<Outlet> outlet;


    public Restaurant(RestaurantCreateRequest request){
        this.setAddress(new Address(request.addressDetails()));
        this.setRating(5);
        this.setName(request.name());
    }
}
