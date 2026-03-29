package com.twilight.objects;


import jakarta.persistence.*;

@Entity
public class Manager {
    @Id
    @Column(name = "mob_no", length = 15)
    private String mobNo;

    @OneToOne
    @JoinColumn(name = "mob_no")
    @MapsId
    private User user;
}
