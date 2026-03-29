package com.twilight.objects;

import com.twilight.dataTransferObjects.RestaurantR;
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
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String image;

    @OneToOne
    @JoinColumn(name = "mob_no")
    private Merchant merchant;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;

    @OneToMany(mappedBy = "restaurant")
    private List<Outlet> outlet;


    public Restaurant(RestaurantR request){
        this.setAddress(new Address(request.addressR()));
        this.setName(request.name());
    }
}
