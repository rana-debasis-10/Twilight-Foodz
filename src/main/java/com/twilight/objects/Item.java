package com.twilight.objects;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1)
    @Max(6)
    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    @NotNull
    private Double subtotal;

    @NotNull
    private Integer foodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @NotNull
    private Order order;


}

