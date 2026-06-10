package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.OutletStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter

@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "outlet_location", // Optional: Gives your constraint a custom database name
                        columnNames = {"longitude", "latitude"} // Must match the actual database column names
                )
        }
)
public class Outlet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL)
    private List<Food> foods;

    private Double longitude, latitude;

    @Enumerated(EnumType.STRING)
    private OutletStatus outletStatus;

    @Column(unique = true)
    @MobileNumber
    private String merchant;

    @MobileNumber
    @Column(unique = true)
    private String manager;


}
