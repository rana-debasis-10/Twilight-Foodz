package com.twilight.components.database;
import com.twilight.dataTransferObjects.request.AddressDetails;
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
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String landMark;

    @OneToOne(mappedBy = "address")
    private CustomerOrder order;
    @OneToOne(mappedBy = "address")
    private Outlet outlet;


    public Address (AddressDetails request){
        this.street = request.street();
        this.city = request.city();
        this.state = request.state();
        this.country = request.country();
        this.postalCode = request.postalCode();
        this.landMark = request.landMark();
    }
}
