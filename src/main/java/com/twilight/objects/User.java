package com.twilight.objects;

import com.twilight.annotations.*;
import com.twilight.types.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @MobileNumber
    @NotNull(message = "Enter your mobile number")
    private String mobNo;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Driver driver;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Merchant merchant;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Manager manager;
}
