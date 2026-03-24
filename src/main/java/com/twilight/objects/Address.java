package com.twilight.objects;
import com.twilight.dataTransferObjects.AddressR;
import com.twilight.types.AddressType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AddressType type;

    private String country;
    private String state;
    private String city;
    private String postalCode;
    private String street;
    private String landMark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Customer customer;

    @OneToOne(mappedBy = "address")
    private Order order;
    @OneToOne(mappedBy = "address")
    private Outlet outlet;


    public Address(AddressR address){
        this.street = address.street();
        this.city = address.city();
        this.state = address.state();
        this.country = address.country();
        this.postalCode = address.postalCode();
        this.landMark = address.landMark();
    }
}
