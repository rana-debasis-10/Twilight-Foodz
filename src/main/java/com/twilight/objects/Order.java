package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.DeliveryStatus;
import com.twilight.types.PaymentMethod;
import com.twilight.types.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
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

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "mob_no")
    private Customer customer;

    @MobileNumber
    private String deliveryMobNo;

    private String outletId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private OrderAddress deliveryAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items;

}
