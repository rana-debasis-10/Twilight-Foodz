package com.twilight.objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;
    private String city;
    private String pinCode;
    private String street;
    private String landMark;

    @ManyToOne
    @JoinColumn(name = "mob_no")
    private Customer customer;
}
