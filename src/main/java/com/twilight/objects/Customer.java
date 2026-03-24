package com.twilight.objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    String mobNo;

    @MapsId
    @JoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private User user;


    @NotNull(message = "Enter your name")
    private String name;

    private Boolean addressAdded;

    @OneToOne
    @JoinColumn(name = "addressId")
    private Address primaryAddress;

    @OneToMany(mappedBy = "customer")
    private List<Address> allAddresses;
}
