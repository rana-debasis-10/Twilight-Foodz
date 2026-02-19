package com.twilight.objects.database;

import com.twilight.annotations.*;
import com.twilight.dataTransferObjects.authentication.BasicUserDetails;
import com.twilight.types.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull(message = "Enter your name")
    private String name;

    @ValidEmail
    @Column(unique = true)
    @NotNull(message = "Enter your email")
    private String email;

    @ValidMobileNumber
    @NotNull(message = "Enter your mobile number")
    private String mobNo;

    @Column(nullable = false)
    private String password;

    @Enumerated (EnumType.STRING)
    private Role role;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired=true;
    private boolean enabled = true;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders=new ArrayList<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Driver driver;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Merchant merchant;

    @OneToOne(mappedBy = "restaurantAdmin",cascade = CascadeType.ALL)
    private Outlet outlet;

    public Customer(BasicUserDetails details){
        this.name = details.name();
        this.email= details.email();
        this.mobNo = details.mobNo();
        this.role = Role.CUSTOMER;
        this.password = details.password();
    }

}
