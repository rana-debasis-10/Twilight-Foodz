package com.twilight.objects;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;

    @Nullable
    private Double priceOverride;
    @NotNull
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "outlet_id")
    @NotNull
    private Outlet outlet;

}
