package com.twilight.components.database;

import com.twilight.annotations.ValidEmail;
import com.twilight.annotations.ValidMobileNumber;
import com.twilight.dataTransferObjects.request.CustomerOrderRequest;
import com.twilight.types.Currency;
import com.twilight.types.DeliveryStatus;
import com.twilight.types.PaymentMethod;
import com.twilight.types.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String razorpayOrderId;

    private Double total;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String receipt;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ValidMobileNumber
    private String mobNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items;

    public CustomerOrder(CustomerOrderRequest orderDetails, String receipt , Currency currency) {
        this.createdAt = Instant.now();
        this.paymentMethod = orderDetails.paymentMethod();
        this.deliveryStatus = DeliveryStatus.ORDER_MADE;
        this.paymentStatus = PaymentStatus.PENDING;
        this.address = new Address( orderDetails .addressDetails());
        this.receipt = receipt;
        this.currency = currency;
    }
}
