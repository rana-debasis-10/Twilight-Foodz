package com.twilight.objects;


import jakarta.persistence.*;

@Entity
public class Manager {
    @Id
    @Column(name = "mob_no", length = 15)
    private String mobNo;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="outlet_id")
    private Outlet outlet;
}
