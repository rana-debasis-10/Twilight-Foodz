package com.twilight.objects.database;

import com.twilight.types.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Outlet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
            @JoinColumn
    Merchant merchant;

    @OneToOne
    @JoinColumn
    Customer restaurantAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Restaurant restaurant;

    @OneToMany
    private List<Food> foods;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Location location;

    @Enumerated(EnumType.STRING)
    private Status status;


}
