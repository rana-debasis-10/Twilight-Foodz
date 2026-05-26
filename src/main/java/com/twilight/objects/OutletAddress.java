package com.twilight.objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OutletAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;
    private String city;
    private String pinCode;
    private String street;
    private String landMark;

    @OneToOne
    @JoinColumn(name = "outlet_address")

    private Outlet outlet;
}