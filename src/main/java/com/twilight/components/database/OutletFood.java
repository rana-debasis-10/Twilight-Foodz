package com.twilight.components.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OutletFood {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Food food;

    private Double priceOverride;
    private String outletLocation;
    private boolean available;

    @ManyToOne
    @JoinColumn
    private Outlet outlet;

}
