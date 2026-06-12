package com.twilight.objects;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String state;
    @NotNull
    private String city;
    @NotNull
    private String pinCode;
    @Nullable
    private String street;
    @Nullable
    private String landMark;

    @OneToOne(mappedBy = "deliveryAddress")
    @NotNull
    private Order order;




}
