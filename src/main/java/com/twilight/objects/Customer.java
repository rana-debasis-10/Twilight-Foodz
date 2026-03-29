package com.twilight.objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import com.twilight.annotations.MobileNumber;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @MobileNumber
    @Column(name = "mob_no", length = 15)
    private String mobNo;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "mob_no")
    private User user;


    @NotNull(message = "Enter your name")
    private String name;

    private boolean addressAdded;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> address;

}
