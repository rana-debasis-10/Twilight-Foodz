package com.twilight.objects;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Please enter a name")
    private String name;

    @NotNull(message = "Please enter a price")
    private Double price;

    @NotNull
    private String image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Food> food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

}
