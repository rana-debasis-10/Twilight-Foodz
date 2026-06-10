package com.twilight.objects;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;
    private String city;
    private String pinCode;
    private String street;
    private String landMark;

    @OneToOne(mappedBy = "deliveryAddress")
    private Order order;




}
