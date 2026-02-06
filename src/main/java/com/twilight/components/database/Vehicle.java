package com.twilight.components.database;

import com.twilight.dataTransferObjects.request.VehicleRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private final String type;

    @OneToOne
    @JoinColumn(name = "driverId")
    private Driver driver;

    public Vehicle(VehicleRequest request){
        this.type = request.type();
    }
}
