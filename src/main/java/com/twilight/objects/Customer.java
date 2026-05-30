package com.twilight.objects;

import com.twilight.types.AddressType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CustomerAddress> addresses ;


}
