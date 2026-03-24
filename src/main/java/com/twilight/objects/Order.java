package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.OrderR;
import com.twilight.types.DeliveryStatus;
import com.twilight.types.PaymentMethod;
import com.twilight.types.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String razorpayOrderId;

    private Double total;

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
    private User user;

    @MobileNumber
    private String mobNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items;

    public Order(OrderR orderDetails, String receipt ) {
        this.createdAt = Instant.now();
        this.paymentMethod = orderDetails.paymentMethod();
        this.deliveryStatus = DeliveryStatus.ordered;
        this.paymentStatus = PaymentStatus.pending;
        this.address = new Address( orderDetails .addressR());
        this.receipt = receipt;
    }
}
