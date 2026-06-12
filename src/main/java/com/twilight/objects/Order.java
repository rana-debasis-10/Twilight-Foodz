package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.DeliveryStatus;
import com.twilight.types.PaymentMethod;
import com.twilight.types.PaymentStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @Nullable
    private String razorpayOrderId;

    @NotNull
    private Double total;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private PaymentMethod paymentMethod;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private DeliveryStatus deliveryStatus;

    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "mob_no")
    @NotNull
    private Customer customer;

    @MobileNumber
    @NotNull
    private String deliveryMobNo;

    @NotNull
    private Integer outletId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @NotNull
    private OrderAddress deliveryAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items;

}
