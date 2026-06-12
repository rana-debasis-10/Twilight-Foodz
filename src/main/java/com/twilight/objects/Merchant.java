package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.DriverR;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Merchants")
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {

    @NotNull
    String name;

    @Id
    @MobileNumber
    @Column(unique = true)
    private String mobNo;

    @NotNull
    @Column(unique = true)
    private String email;


    @OneToOne(mappedBy = "merchant", cascade = CascadeType.ALL)
    private Restaurant restaurant;

}

