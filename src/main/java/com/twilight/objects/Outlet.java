package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.OutletStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

@Table(uniqueConstraints = {
                @UniqueConstraint(
                        name = "outlet_location",
                        columnNames = {"longitude", "latitude"}
                )
        }
)
public class Outlet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL)
    private List<Food> foods;

    @NotNull
    private Double longitude;
    @NotNull
    private Double latitude;

    @Enumerated(EnumType.STRING)
    private OutletStatus outletStatus;

    @NotNull
    @MobileNumber
    private String merchantMobNo;

    @Column(unique = true)
    @Nullable
    @MobileNumber
    private String managerMobNo;

}
