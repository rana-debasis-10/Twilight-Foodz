package com.twilight.objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String state;
    @NotNull
    private String city;
    @NotNull
    private String pinCode;
    private String street;
    private String landMark;

    @ManyToOne
    @JoinColumn(name = "mob_no")
    @NotNull
    private Customer customer;
}
