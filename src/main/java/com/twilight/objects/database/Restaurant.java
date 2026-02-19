package com.twilight.objects.database;

import com.twilight.dataTransferObjects.request.RestaurantCreateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<Product> products = new ArrayList<>();

    /// AddressDetails of the restaurant
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn
    private Location location;


    @Min(value = 0)
    @Max(value = 5)
    private int rating;

    @OneToMany
    private List<Outlet> outlet;


    public Restaurant(RestaurantCreateRequest request){
        this.setLocation(new Location(request.addressDetails()));
        this.setRating(5);
        this.setName(request.name());
    }
}
