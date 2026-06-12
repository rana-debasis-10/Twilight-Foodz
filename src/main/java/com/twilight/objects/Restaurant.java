package com.twilight.objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String image;

    @NotNull
    private String fssai;

    @OneToOne
    @JoinColumn(name = "mob_no")
    @NotNull
    private Merchant merchant;

    @NotNull
    boolean menuAdded;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<Outlet> outlet = new ArrayList<>();

}
