package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.Role;
import jakarta.persistence.*;

@Entity
public class OutletMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @MobileNumber
    String mobNo;

    @Enumerated(value = EnumType.STRING)
    Role role;

    @ManyToOne
    @JoinColumn(name = "outlet_id")
    Outlet outlet;
}
