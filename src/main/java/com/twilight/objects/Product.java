package com.twilight.objects;

import com.twilight.dataTransferObjects.FoodR;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull(message = "Please enter a name")
    private String name;

    @NotNull(message = "Please enter a price")
    private Double price;

    private String image;

    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Food> food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Restaurant restaurant;


    public Product(FoodR request){
        this.name=request.name();
        this.price= request.price();
        this.description= request.description();
    }
}
