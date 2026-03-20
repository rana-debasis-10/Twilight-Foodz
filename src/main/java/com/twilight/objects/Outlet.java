package com.twilight.objects;

import com.twilight.types.OutletStatus;
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
    User restaurantAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Restaurant restaurant;

    @OneToMany
    private List<Food> foods;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Address address;

    @Enumerated(EnumType.STRING)
    private OutletStatus outletStatus;


}
