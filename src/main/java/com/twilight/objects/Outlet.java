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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "mob_no")
    Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL)
    private List<Food> foods;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "outlet_address_id")
    private OutletAddress outletAddress;

    @Enumerated(EnumType.STRING)
    private OutletStatus outletStatus;


}
