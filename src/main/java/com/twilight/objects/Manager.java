package com.twilight.objects;


import jakarta.persistence.*;

@Entity
public class Manager {
    @Id
    private String mobNo;

    @OneToOne
    @JoinColumn
    @MapsId
    private User user;
}
